import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;  // добавлен импорт

public class ParallelCounter {

    public static <T> int countOccurrences(Collection<T> collection, T element) {
        if (collection == null || collection.isEmpty()) {
            return 0;
        }

        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int totalSize = collection.size();
        int chunkSize = (int) Math.ceil((double) totalSize / numThreads);

        List<Iterable<T>> chunks = divideIntoChunks(collection, chunkSize);

        List<Callable<Integer>> tasks = new ArrayList<>();
        for (Iterable<T> chunk : chunks) {
            tasks.add(() -> {
                int count = 0;
                for (T item : chunk) {
                    if (Objects.equals(item, element)) {
                        count++;
                    }
                }
                return count;
            });
        }

        int total = 0;
        try {
            List<Future<Integer>> results = executor.invokeAll(tasks);
            for (Future<Integer> future : results) {
                total += future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Error during parallel counting of occurrences", e);
        } finally {
            executor.shutdown();
        }

        return total;
    }

    private static <T> List<Iterable<T>> divideIntoChunks(Collection<T> collection, int chunkSize) {
        List<Iterable<T>> chunks = new ArrayList<>();
        Iterator<T> iterator = collection.iterator();

        while (iterator.hasNext()) {
            List<T> chunk = new ArrayList<>();
            for (int i = 0; i < chunkSize && iterator.hasNext(); i++) {
                chunk.add(iterator.next());
            }
            chunks.add(chunk);
        }
        return chunks;
    }

    // Новый метод для подсчёта по полю
    public static <T, R> int countByField(List<T> list, Function<T, R> extractor, R value) {
        return (int) list.parallelStream()
                .filter(item -> extractor.apply(item).equals(value))
                .count();
    }
}