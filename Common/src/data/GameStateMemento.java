package data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.gsonfire.GsonFireBuilder;
import io.gsonfire.TypeSelector;

/**
 * Created by hp on 13/12/2015.
 */
public class GameStateMemento {

    private String state;

    public GameStateMemento(GameState state) {
        this.state = new Gson().toJson(state);
    }

    public GameState getState() {
        return makeGsonForeCells().fromJson(state, GameState.class);
    }

    private Gson makeGsonForeCells() {
        GsonFireBuilder builder = new GsonFireBuilder()
                .registerTypeSelector(CellBase.class, new TypeSelector<CellBase>() {
                    @Override
                    public Class<? extends CellBase> getClassForElement(JsonElement readElement) {
                        String type = readElement.getAsJsonObject().get("name").getAsString();
                        if(type.equals(MineCell.class.getSimpleName())){
                            return MineCell.class;
                        } else if(type.equals(NumCell.class.getSimpleName())) {
                            return NumCell.class;
                        } else {
                            return EmptyCell.class; //returning null will trigger Gson's default behavior
                        }
                    }
                });
        return builder.createGson();
    }
}
