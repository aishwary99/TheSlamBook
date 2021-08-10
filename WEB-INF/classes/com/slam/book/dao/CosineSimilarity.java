package com.slam.book.dao;
import java.util.*;
import java.io.*;
class CosineSimilarity
{
public static Map<String, Integer> getTermFrequencyMap(String[] terms) 
{
Map<String, Integer> termFrequencyMap = new HashMap<>();
for(String term : terms) 
{
Integer n = termFrequencyMap.get(term);
n = (n == null) ? 1 : ++n;
termFrequencyMap.put(term, n);
}
return termFrequencyMap;
}
public static double cosineSimilarity(String text1, String text2) 
{
Map<String, Integer> a = getTermFrequencyMap(text1.split("\\W+"));
Map<String, Integer> b = getTermFrequencyMap(text2.split("\\W+"));
HashSet<String> intersection = new HashSet<>(a.keySet());
intersection.retainAll(b.keySet());
double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;
for(String item : intersection) 
{
dotProduct += a.get(item) * b.get(item);
}
for(String k : a.keySet()) 
{
magnitudeA += Math.pow(a.get(k), 2);
}
for(String k : b.keySet()) 
{
magnitudeB += Math.pow(b.get(k), 2);
}
return dotProduct / Math.sqrt(magnitudeA * magnitudeB);
}
}