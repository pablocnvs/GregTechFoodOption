package com.bruberu.gregtechfoodoption.tools;

import com.bruberu.gregtechfoodoption.item.GTFOMetaItem;
import gregtech.api.items.ToolDictNames;
import gregtech.api.items.toolitem.ToolMetaItem;
import gregtech.api.recipes.ModHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.SolidMaterial;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class GTFOMetaTool extends ToolMetaItem<ToolMetaItem<?>.MetaToolValueItem> {

    @Override
    public void registerSubItems() {
        GTFOMetaItem.ROLLING_PIN = addItem(0, "tool.rolling_pin").setToolStats(new ToolRollingPin())
                .setFullRepairCost(2)
                .addOreDict(ToolDictNames.craftingToolRollingPin);
    }

    public void registerRecipes() {
        SolidMaterial[] rollingPinMaterials = new SolidMaterial[]{
                Materials.Wood, Materials.Rubber, Materials.Plastic, Materials.Polytetrafluoroethylene
        };
        for (int i = 0; i < rollingPinMaterials.length; i++) {
            SolidMaterial solidMaterial = rollingPinMaterials[i];
            ItemStack itemStack = GTFOMetaItem.ROLLING_PIN.getStackForm();
            GTFOMetaItem.ROLLING_PIN.setToolData(itemStack, solidMaterial, 128 * (1 << i), 1, 1.0f, 1f);
            if(i != 0)
                ModHandler.addShapedRecipe(String.format("rolling_pin_%s", solidMaterial.toString()), itemStack,
                    "  R", " I ", "R f",
                    'I', new UnificationEntry(OrePrefix.ingot, solidMaterial),
                    'R', new UnificationEntry(OrePrefix.stick, Materials.Iron));
            else
                ModHandler.addShapedRecipe("rolling_pin_wood", itemStack,
                        "  R", " P ", "R f",
                        'P', new UnificationEntry(OrePrefix.plank, Materials.Wood),
                        'R', new UnificationEntry(OrePrefix.stick, Materials.Iron));
        }
    }

}
