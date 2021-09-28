package online.nasgar.skywars.api.service;

/**
 * Represents the model to start and stop services
 */
public interface Service {

    /**
     * Start the service
     */
    void start();

    /**
     * Stop the service
     */
    void stop();
}
