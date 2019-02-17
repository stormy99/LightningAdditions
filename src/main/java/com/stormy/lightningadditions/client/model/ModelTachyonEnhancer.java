/*
 * ********************************************************************************
 * Copyright (c) 2017 StormyMode, MiningMark48. All Rights Reserved!
 * This file is part of Lightning Additions (MC-Mod).
 *
 * This project cannot be copied and/or distributed without the express
 * permission of StormyMode, MiningMark48 (Developers)!
 * ********************************************************************************
 */

package com.stormy.lightningadditions.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTachyonEnhancer extends ModelBiped
{
  //fields
  private ModelRenderer TachP1;
  private ModelRenderer TachP2;
  private ModelRenderer TachP3;
  private ModelRenderer TachP4;
  private ModelRenderer TachP5;
  private ModelRenderer TachP6;
  private ModelRenderer TachP7;
  private ModelRenderer TachP8;
  private ModelRenderer TachP9;
  
  public ModelTachyonEnhancer(float scale)
  {

    super(scale, 0, 64, 64);

    textureWidth = 64;
    textureHeight = 64;
    
    TachP1 = new ModelRenderer(this, 11, 43);
    TachP1.addBox(-3F, 3F, -4F, 1, 4, 2);
    TachP1.setRotationPoint(0F, 0F, 0F);
    TachP1.setTextureSize(64, 64);
    TachP1.mirror = true;
    setRotation(TachP1, 0F, 0F, 0F);
    TachP2 = new ModelRenderer(this, 11, 43);
    TachP2.addBox(2F, 3F, -4F, 1, 4, 2);
    TachP2.setRotationPoint(0F, 0F, 0F);
    TachP2.setTextureSize(64, 64);
    TachP2.mirror = true;
    setRotation(TachP2, 0F, 0F, 0F);
    TachP3 = new ModelRenderer(this, 11, 39);
    TachP3.addBox(-2F, 2F, -4F, 4, 1, 2);
    TachP3.setRotationPoint(0F, 0F, 0F);
    TachP3.setTextureSize(64, 64);
    TachP3.mirror = true;
    setRotation(TachP3, 0F, 0F, 0F);
    TachP4 = new ModelRenderer(this, 11, 39);
    TachP4.addBox(-2F, 7F, -4F, 4, 1, 2);
    TachP4.setRotationPoint(0F, 0F, 0F);
    TachP4.setTextureSize(64, 64);
    TachP4.mirror = true;
    setRotation(TachP4, 0F, 0F, 0F);
    TachP5 = new ModelRenderer(this, 0, 39);
    TachP5.addBox(-2F, 3F, -3F, 4, 4, 1);
    TachP5.setRotationPoint(0F, 0F, 0F);
    TachP5.setTextureSize(64, 64);
    TachP5.mirror = true;
    setRotation(TachP5, 0F, 0F, 0F);
    TachP6 = new ModelRenderer(this, 0, 45);
    TachP6.addBox(3F, 3F, -3F, 1, 1, 1);
    TachP6.setRotationPoint(0F, 0F, 0F);
    TachP6.setTextureSize(64, 64);
    TachP6.mirror = true;
    setRotation(TachP6, 0F, 0F, 0F);
    TachP7 = new ModelRenderer(this, 0, 45);
    TachP7.addBox(3F, 6F, -3F, 1, 1, 1);
    TachP7.setRotationPoint(0F, 0F, 0F);
    TachP7.setTextureSize(64, 64);
    TachP7.mirror = true;
    setRotation(TachP7, 0F, 0F, 0F);
    TachP8 = new ModelRenderer(this, 0, 45);
    TachP8.addBox(-4F, 6F, -3F, 1, 1, 1);
    TachP8.setRotationPoint(0F, 0F, 0F);
    TachP8.setTextureSize(64, 64);
    TachP8.mirror = true;
    setRotation(TachP8, 0F, 0F, 0F);
    TachP9 = new ModelRenderer(this, 0, 45);
    TachP9.addBox(-4F, 3F, -3F, 1, 1, 1);
    TachP9.setRotationPoint(0F, 0F, 0F);
    TachP9.setTextureSize(64, 64);
    TachP9.mirror = true;
    setRotation(TachP9, 0F, 0F, 0F);

    bipedBody.addChild(TachP1);
    bipedBody.addChild(TachP2);
    bipedBody.addChild(TachP3);
    bipedBody.addChild(TachP4);
    bipedBody.addChild(TachP5);
    bipedBody.addChild(TachP6);
    bipedBody.addChild(TachP7);
    bipedBody.addChild(TachP8);
    bipedBody.addChild(TachP9);

  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    TachP1.render(f5);
    TachP2.render(f5);
    TachP3.render(f5);
    TachP4.render(f5);
    TachP5.render(f5);
    TachP6.render(f5);
    TachP7.render(f5);
    TachP8.render(f5);
    TachP9.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
}
