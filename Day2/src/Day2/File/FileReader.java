package Day2.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

public class FileReader {
	public Optional<String> readFirstLine(Path path) throws IOException {
		Objects.requireNonNull(path, "path must not be null");
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			return Optional.ofNullable(reader.readLine());
		}
	}
	public static void main(String[] args) throws IOException {
		FileReader f = new FileReader();
		Path tempFile = Files.createTempFile("sample", ".txt");
		Files.writeString(tempFile, "hello hello", StandardCharsets.UTF_8);
		f.readFirstLine(tempFile).ifPresentOrElse(
			    line -> System.out.println(line),
			    ()   -> System.out.println("File is empty")
			);
	}

}
