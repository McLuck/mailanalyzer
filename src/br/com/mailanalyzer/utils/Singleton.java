package br.com.mailanalyzer.utils;

/**
 *
 * @author McLuck
 */
public interface Singleton<T extends Class> {
    public T getInstance();
}
