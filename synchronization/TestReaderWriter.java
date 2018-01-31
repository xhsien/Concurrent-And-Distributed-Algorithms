package synchronization;

public class TestReaderWriter {
    private int totalReader = 0;
    private int totalWriter = 0;

    private Reader[] readers;
    private Writer[] writers;

    public TestReaderWriter(int numReader, int numWriter, ReaderWriterMonitor monitor) {
        totalReader = numReader;
        totalWriter = numWriter;

        readers = new Reader[totalReader];
        for (int i = 0; i < totalReader; i++) {
            readers[i] = new Reader(i, monitor);
        }

        writers = new Writer[totalWriter];
        for (int i = 0; i < totalWriter; i++) {
            writers[i] = new Writer(i, monitor);
        }
    }

    public static void main(String[] args) {
        ReaderWriterMonitor monitor = new ReaderWriterMonitor();
        new TestReaderWriter(5, 2, monitor);
    }
}
