package pm.c7.skinblinker;

import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import me.sargunvohra.mcmods.autoconfig1u.serializer.PartitioningSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.render.entity.PlayerModelPart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class FlashyClothing implements ClientModInitializer {
    public static final String MOD_ID = "skinblinker";
    private static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static FlashyClothing INSTANCE;

    public FlashyClothingConfig config;

    private final Map<PlayerModelPart, Integer> intervals;

    public FlashyClothing(){
        FlashyClothing.INSTANCE = this;
        this.intervals = new HashMap<PlayerModelPart,Integer>();
        for (PlayerModelPart part : PlayerModelPart.values()) {
            this.intervals.put(part, 0);
        }
    }

    @Override
    public void onInitializeClient() {
        AutoConfig.register(
                FlashyClothingConfig.class,
                PartitioningSerializer.wrap(JanksonConfigSerializer::new)
        );

        this.config = AutoConfig.getConfigHolder(FlashyClothingConfig.class).getConfig();

        ClientTickCallback.EVENT.register(client -> {
            if (this.config.main.enabled) {
                for (Map.Entry<PlayerModelPart,Integer> interval : this.intervals.entrySet()) {
                    if (!this.config.main.getPartEnabled(interval.getKey())) {
                        continue;
                    }

                    int counter = this.intervals.get(interval.getKey());
                    if (counter++ >= this.config.main.getInterval(interval.getKey())) {
                        this.intervals.put(interval.getKey(), 0);
                        client.options.togglePlayerModelPart(interval.getKey());
                    }else{
                        this.intervals.put(interval.getKey(), counter);
                    }
                }
            }
        });
    }
}