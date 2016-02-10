package data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.gsonfire.GsonFireBuilder;
import io.gsonfire.TypeSelector;

/**
 * Created by hp on 12/12/2015.
 */
public class GameLevel {

    public LevelBase level;

    public GameLevel(LevelBase level) {
        this.level = level;
    }

    public static GameLevel fromJson(String json) {
        return makeGsonForLevel().fromJson(json, GameLevel.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameLevel gameLevel = (GameLevel) o;

        if (level.rows != gameLevel.level.rows) return false;
        if (level.cols != gameLevel.level.cols) return false;
        return level.mines == gameLevel.level.mines;

    }

    @Override
    public String toString() {
        return "GameLevel{" +
                "level=" + level +
                '}';
    }

    private static Gson makeGsonForLevel() {
        GsonFireBuilder builder = new GsonFireBuilder()
                .registerTypeSelector(LevelBase.class, new TypeSelector<LevelBase>() {
                    @Override
                    public Class<? extends LevelBase> getClassForElement(JsonElement readElement) {
                        String type = readElement.getAsJsonObject().get("name").getAsString();
                        if (type.equals(BeginnerLevel.class.getSimpleName())) {
                            return BeginnerLevel.class;
                        } else if (type.equals(IntermediateLevel.class.getSimpleName())) {
                            return IntermediateLevel.class;
                        } else if (type.equals(ExpertLevel.class.getSimpleName())) {
                            return ExpertLevel.class;
                        } else {
                            return CustomLevel.class;
                        }
                    }
                });
        return builder.createGson();
    }
}