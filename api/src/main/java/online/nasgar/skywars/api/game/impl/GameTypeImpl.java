package online.nasgar.skywars.api.game.impl;

import com.google.gson.annotations.Expose;
import online.nasgar.skywars.api.game.type.GameType;

public class GameTypeImpl implements GameType {

    @Expose
    private Integer teamSize;
    @Expose
    private Types type;

    public GameTypeImpl(Integer teamSize, Types type){
        this.teamSize = teamSize;
        this.type = type;
    }

    @Override
    public Integer getSize() {
        return teamSize;
    }

    @Override
    public void setSize(Integer newSize) {
        this.teamSize = newSize;
    }

    @Override
    public Types getType() {
        return type;
    }

    @Override
    public void setType(Types newType) {
        this.type = newType;
    }
}
