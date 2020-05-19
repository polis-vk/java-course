package ru.mail.polis.patterns.behavioral.observer;

/**
 * Observer interface.
 */
public interface WeatherObserver {

    void update(WeatherType currentWeather);

}
