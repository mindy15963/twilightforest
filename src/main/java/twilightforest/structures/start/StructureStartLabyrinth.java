package twilightforest.structures.start;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import twilightforest.TFFeature;
import twilightforest.structures.minotaurmaze.ComponentTFMazeRuins;

import java.util.Random;

import static twilightforest.TFFeature.LABYRINTH;

public class StructureStartLabyrinth extends StructureStartTFFeatureAbstract {
    public StructureStartLabyrinth() {
        super();
    }

    public StructureStartLabyrinth(World world, TFFeature feature, Random rand, int chunkX, int chunkZ) {
        super(world, feature, rand, chunkX, chunkZ);
    }

    @Override
    protected StructurePiece makeFirstComponent(World world, TFFeature feature, Random rand, int x, int y, int z) {
        return new ComponentTFMazeRuins(LABYRINTH, world, rand, 0, x, y, z);
    }
}