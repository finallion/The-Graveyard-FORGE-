package com.finallion.graveyard.init;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.biomes.HauntedForestBiomes;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = TheGraveyard.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TGBiomes {
    /*
    private static final Map<ResourceLocation, Biome> BIOMES = new HashMap<>();

    public static final RegistryKey<Biome> HAUNTED_FOREST = add("haunted_forest", HauntedForestBiomes.HauntedForestBiome());
    public static final RegistryKey<Biome> HAUNTED_LAKES = add("haunted_lakes", HauntedForestBiomes.HauntedForestLakeBiome());
    public static final RegistryKey<Biome> ERODED_HAUNTED_FOREST = add("eroded_haunted_forest", HauntedForestBiomes.ErodedHauntedForestBiome());

    private static RegistryKey<Biome> add(String name, Biome biome) {
        ResourceLocation id = new ResourceLocation(TheGraveyard.MOD_ID, name);
        biome.setRegistryName(id);
        BIOMES.put(id, biome);

        return RegistryKey.create(Registry.BIOME_REGISTRY, id);
    }


    @SubscribeEvent()
    public static void onBiomeRegister(RegistryEvent.Register<Biome> event) {
        IForgeRegistry<Biome> biomeRegistry = event.getRegistry();
        BIOMES.values().forEach(biomeRegistry::register);

        registerBiome(HAUNTED_FOREST, 3, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        registerBiome(HAUNTED_LAKES, 3, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);
        registerBiome(ERODED_HAUNTED_FOREST, 2, BiomeManager.BiomeType.COOL, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.OVERWORLD);

    }

    private static void registerBiome(RegistryKey<Biome> key, int weight, BiomeManager.BiomeType type, BiomeDictionary.Type... types) {
        if (weight > 0) {
            BiomeManager.addBiome(type, new BiomeManager.BiomeEntry(key, weight));
        }

        BiomeDictionary.addTypes(key, types);
    }


     */

    public static List<Biome> BIOMES = new ArrayList<>();

    public static Biome HAUNTED_FOREST = createBiome("haunted_forest", HauntedForestBiomes.HauntedForestBiome());
    public static Biome HAUNTED_LAKES = createBiome("haunted_lakes", HauntedForestBiomes.HauntedForestLakeBiome());
    public static Biome ERODED_HAUNTED_FOREST = createBiome("eroded_haunted_forest", HauntedForestBiomes.ErodedHauntedForestBiome());

    public static Biome createBiome(String id, Biome biome) {
        ResourceLocation resourceLocation = new ResourceLocation(TheGraveyard.MOD_ID, id);

        biome.setRegistryName(resourceLocation);

        BIOMES.add(biome);

        return biome;
    }

    public static void addToDictionaries() {
        BIOMES.forEach((biome -> BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()), 2))));
        BIOMES.forEach((biome -> BiomeDictionary.addTypes(RegistryKey.create(Registry.BIOME_REGISTRY, biome.getRegistryName()), BiomeDictionary.Type.OVERWORLD)));
    }


    @SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
        addToDictionaries();
        BIOMES.forEach(biome -> event.getRegistry().register(biome));
    }
}