package its_meow.betteranimalsplus.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import its_meow.betteranimalsplus.client.model.ModelBoarHead;
import its_meow.betteranimalsplus.client.model.ModelDeerHead;
import its_meow.betteranimalsplus.client.model.ModelFoxHead;
import its_meow.betteranimalsplus.client.model.ModelHirschgeistSkull;
import its_meow.betteranimalsplus.client.model.ModelReindeerHead;
import its_meow.betteranimalsplus.client.model.ModelWolfHead;
import its_meow.betteranimalsplus.common.block.BlockGenericSkull;
import its_meow.betteranimalsplus.common.item.ItemBlockHeadType;
import its_meow.betteranimalsplus.common.tileentity.TileEntityHead;
import its_meow.betteranimalsplus.init.ModTextures;
import net.minecraft.client.model.ModelBase;

public enum HeadTypes {


	// Double supplier avoids loading ModelBase which does not exist on the server
	WOLFHEAD("wolfhead", true, 4, () -> () -> ModelWolfHead.class,
            type -> new TileEntityHead(type, 0F, ModTextures.wolf_black, ModTextures.wolf_snowy,
                    ModTextures.wolf_timber, ModTextures.coyote_hostile)),

    FOXHEAD("foxhead", true, 4, () -> () -> ModelFoxHead.class,
            type -> new TileEntityHead(type, -0.1F, ModTextures.fox_1, ModTextures.fox_2, ModTextures.fox_3,
                    ModTextures.fox_4)),

    BOARHEAD("boarhead", false, 4, () -> () -> ModelBoarHead.class,
            type -> new TileEntityHead(type, 0F, ModTextures.boar_1, ModTextures.boar_2, ModTextures.boar_3,
                    ModTextures.boar_4)),

    DEERHEAD("deerhead", false, 2, () -> () -> ModelDeerHead.class, type -> new TileEntityHead(type, 0F, (typeNum -> {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
            return typeNum == 1 ? ModTextures.deer_1_christmas : ModTextures.deer_2_christmas;
        } else {
            return typeNum == 1 ? ModTextures.deer_1 : ModTextures.deer_2;
        }

    }))),

    REINDEERHEAD("reindeerhead", false, 4, () -> () -> ModelReindeerHead.class,
            type -> new TileEntityHead(type, 0F, (typeNum -> {
                if (typeNum <= 4) {
                    return null;
                } else {
                    switch (typeNum) {
                    case 5:
                        return ModTextures.reindeer_1_christmas;
                    case 6:
                        return ModTextures.reindeer_2_christmas;
                    case 7:
                        return ModTextures.reindeer_3_christmas;
                    case 8:
                        return ModTextures.reindeer_4_christmas;
                    default:
                        return ModTextures.reindeer_1;
                    }
                }
            }), ModTextures.reindeer_1, ModTextures.reindeer_2, ModTextures.reindeer_3, ModTextures.reindeer_4)),

    HIRSCHGEIST("hirschgeistskull", true, 1, () -> () -> ModelHirschgeistSkull.class,
            type -> new TileEntityHead(type, -0.2F, ModTextures.hirschgeist));



	public final String name;
	public final boolean allowFloor;
	public final int textureCount;
	public final Function<HeadTypes, TileEntityHead> teFactory;
	private final Supplier<Supplier<Class<? extends ModelBase>>> modelSupplier;
	private ArrayList<Pair<BlockGenericSkull, ItemBlockHeadType>> heads = new ArrayList<Pair<BlockGenericSkull, ItemBlockHeadType>>();
	private Set<ItemBlockHeadType> items = new HashSet<ItemBlockHeadType>();
	private BlockGenericSkull block = null;

	HeadTypes(String name, boolean allowFloor, int texCount, Supplier<Supplier<Class<? extends ModelBase>>> modelSupplier,
	          Function<HeadTypes, TileEntityHead> teFactory) {
		this.name = name;
		this.allowFloor = allowFloor;
		this.teFactory = teFactory;
		this.textureCount = texCount;
		this.modelSupplier = modelSupplier;
		block = new BlockGenericSkull(this);
		for (int i = 1; i <= texCount; i++) {
			ItemBlockHeadType item = new ItemBlockHeadType(block, this, i);
			heads.add(Pair.of(block, item));
			items.add(item);
		}
	}

	public BlockGenericSkull getBlock(int i) {
		return heads.get(i).getLeft();
	}

	public ItemBlockHeadType getItem(int i) {
		return heads.get(i).getRight();
	}

	public Set<ItemBlockHeadType> getItemSet() {
		return items;
	}

	public BlockGenericSkull getBlock() {
		return block;
	}

	public Supplier<Supplier<Class<? extends ModelBase>>> getModelSupplier() {
		return modelSupplier;
	}

}