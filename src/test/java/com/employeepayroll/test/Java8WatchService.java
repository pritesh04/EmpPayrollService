package com.employeepayroll.test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Modifier;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import WatchEvent.Kind;

public class Java8WatchService {
	private static final String ENTRY_DELETE = null;
	private static final Kind ENTRY_CREATE = null;
	private static final Modifier ENTRY_MODIFY = null;
	private final WatchService watcher;
	private final Map<WatchKey, Path> dirWatchers;

	public Java8WatchService(Path dir) throws IOException {
		super();
		this.watcher = FileSystems.getDefault().newWatchService();
		this.dirWatchers= new HashMap<WatchKey, Path>();
		scanRegisterDirectories(dir);
	}

	private void registerDirWatchers(Path dir) throws IOException {

		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		dirWatchers.put(key, dir);
	}	

	private void scanRegisterDirectories(final Path dir) throws IOException {
		Files.walkFileTree(dir, (FileVisitor<? super Path>) new SimpleFileVisitor<Path>() {

			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				registerDirWatchers(dir);
				return FileVisitResult.CONTINUE;
			}

		});

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	void processEvents() {
		while (true) {
			WatchKey key;
			try {
				key = watcher.take();

			} catch (InterruptedException e) {
				return;
			}
			Path dir = dirWatchers.get(key);
			if (dir == null)
				continue;
			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				Path name = ((WatchEvent<Path>) event).context();
				Path child = dir.resolve(name);
				System.out.format("%s: %s\n", event.kind().name(), child);

				if (kind == ENTRY_CREATE) {
					try {
						if (Files.isDirectory(child))
							scanRegisterDirectories(child);

					} catch (IOException x) {
					}

				} else if (kind.equals(ENTRY_DELETE)) {
					if (Files.isDirectory(child))
						dirWatchers.remove(key);
				}
			}
			boolean valid = key.reset();
			if (!valid) {
				dirWatchers.remove(key);
				if (dirWatchers.isEmpty())
					break;
			}
		}
	}
}
