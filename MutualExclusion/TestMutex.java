package MutualExclusion;

import java.util.Random;

import utils.Util;

public class TestMutex extends Thread {
    private int m_id;
    private Lock m_lock;
    private Random r = new Random();

    private static Object obj = new Object();

    private static int processInCS = 0;

    private TestMutex(int id, Lock lock) {
        m_id = id;
        m_lock = lock;
    }

    private void CriticalSection() {
        synchronized (obj) {
            processInCS++;
            System.out.println(m_id + " enters CS. " + processInCS + " process in CS.");
        }
        Util.sleep(r.nextInt(1000));
    }

    private void nonCriticalSection() {
        synchronized (obj) {
            processInCS--;
            System.out.println(m_id + " exits CS.  " + processInCS + " process in CS.");
        }
        Util.sleep(r.nextInt(1000));
    }

    public void run() {
        while (true) {
            m_lock.requestCS(m_id);
            CriticalSection();
            nonCriticalSection();
            m_lock.releaseCS(m_id);
        }
    }

    public static void main(String[] args) throws Exception {
        // modify parameters for testing
        int n_process = 7;
        int k_mutual = 4;
        Lock lock = new Semaphore(k_mutual);

        TestMutex thread[] = new TestMutex[n_process];

        for (int i = 0; i < n_process; i++) {
            thread[i] = new TestMutex(i, lock);
            thread[i].start();
        }
    }
}