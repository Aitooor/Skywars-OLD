package online.nasgar.skywars.service;

import online.nasgar.skywars.SkyWars;
import online.nasgar.skywars.api.service.Service;

import javax.inject.Inject;

public class SkyWarsService implements Service {

    @Inject private SkyWars skyWars;

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
