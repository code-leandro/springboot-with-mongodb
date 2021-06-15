package com.leandrodev.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamUtil {
	public static boolean areAllNull(Object... objects) {
	     return Stream.of(objects).allMatch(Objects::isNull);
	}

	public static boolean areAllNotNull(Object... objects) {
	     return Stream.of(objects).allMatch(Objects::nonNull);
	}
}
