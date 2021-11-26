package com.stal111.forbidden_arcanus.common.predicate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.common.item.modifier.ItemModifier;
import com.stal111.forbidden_arcanus.common.item.modifier.ModifierHelper;
import com.stal111.forbidden_arcanus.init.other.ModItemModifiers;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;

/**
 * Modifier Item Predicate <br>
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.common.predicate.ModifierItemPredicate
 *
 * @author stal111
 * @version 2.0.0
 * @since 2021-11-25
 */
public class ModifierItemPredicate extends ItemPredicate {

    private final ItemModifier modifier;

    public ModifierItemPredicate(ItemModifier modifier) {
        this.modifier = modifier;
    }

    @Override
    public boolean matches(@Nonnull ItemStack stack) {
        System.out.println(stack);
        System.out.println(this.modifier);
        System.out.println(ModifierHelper.getModifier(stack));
        System.out.println(ModifierHelper.getModifier(stack) == this.modifier);

        return ModifierHelper.getModifier(stack) == this.modifier;
    }

    @Nonnull
    public static ItemPredicate fromJson(@Nullable JsonElement json) {
        if (json == null) {
            return ItemPredicate.ANY;
        }
        ResourceLocation resourceLocation = ResourceLocation.tryParse(GsonHelper.getAsString(json.getAsJsonObject(), "modifier"));
        ItemModifier modifier = ForbiddenArcanus.ITEM_MODIFIER_REGISTRY.get().getValue(resourceLocation);

        if (modifier != null) {
            return new ModifierItemPredicate(modifier);
        }

        return ItemPredicate.ANY;
    }

    @Nonnull
    @Override
    public JsonElement serializeToJson() {
        JsonObject json = new JsonObject();

        json.addProperty("type", new ResourceLocation(ForbiddenArcanus.MOD_ID, "modifier").toString());
        json.addProperty("modifier", Objects.requireNonNull(ModItemModifiers.FIERY.get().getRegistryName()).toString());

        return json;
    }
}
