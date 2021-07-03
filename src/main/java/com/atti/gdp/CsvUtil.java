package com.atti.gdp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvUtil {
	private static CsvMapper csvMapper = new CsvMapper();
	 public static <T> List<T> read(Class<T> aClass, InputStream aStream) throws IOException {
	        CsvSchema schema = csvMapper.schemaFor(aClass).withHeader().withColumnReordering(true);
	        ObjectReader reader = csvMapper.readerFor(aClass).with(schema);
	        return reader.<T>readValues(aStream).readAll();
	    }
}
