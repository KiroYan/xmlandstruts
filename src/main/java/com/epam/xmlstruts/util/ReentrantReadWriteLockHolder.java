package com.epam.xmlstruts.util;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class ReentrantReadWriteLockHolder {
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	private ReentrantReadWriteLockHolder(){}

	public static ReentrantReadWriteLock getLock() {
		return lock;
	}
	
	
}
