package org.marking.lab;

import org.marking.lab.infrastructure.config.SparkConfiguration;

public class Main {
	
	public static void main(String[] args) {
		new SparkConfiguration().init();
	}
}
