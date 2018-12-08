package its_meow.betteranimalsplus.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Squirrel - Batman
 * Created using Tabula 5.1.0
 */
public class ModelSquirrel extends ModelBase {
    public ModelRenderer stomach;
    public ModelRenderer chest;
    public ModelRenderer rLeg;
    public ModelRenderer lLeg01;
    public ModelRenderer tail01;
    public ModelRenderer neck;
    public ModelRenderer rArm01;
    public ModelRenderer lArm01;
    public ModelRenderer fur01;
    public ModelRenderer head;
    public ModelRenderer upperJaw;
    public ModelRenderer lowerJaw;
    public ModelRenderer lEar;
    public ModelRenderer rEar;
    public ModelRenderer snout;
    public ModelRenderer lEarFloof;
    public ModelRenderer rEarFloof;
    public ModelRenderer rArm02a;
    public ModelRenderer rPaw;
    public ModelRenderer rArm02b;
    public ModelRenderer lArm02a;
    public ModelRenderer lPaw;
    public ModelRenderer lArm02b;
    public ModelRenderer rLeg02;
    public ModelRenderer rLeg02a;
    public ModelRenderer rLeg02b;
    public ModelRenderer lFoot;
    public ModelRenderer lLeg02;
    public ModelRenderer lLeg03a;
    public ModelRenderer lLeg03b;
    public ModelRenderer lFoot_1;
    public ModelRenderer tail02;
    public ModelRenderer tail01Fur;
    public ModelRenderer tail02Fur;
    public ModelRenderer tail03;
    public ModelRenderer tail03Fur;
    public ModelRenderer tail04;
    public ModelRenderer tail04Fur;

    public ModelSquirrel() {
        this.textureWidth = 60;
        this.textureHeight = 120;
        this.stomach = new ModelRenderer(this, 0, 0);
        this.stomach.setRotationPoint(0.0F, 14.2F, 2.5F);
        this.stomach.addBox(-3.5F, -3.0F, 0.0F, 7, 6, 7, 0.0F);
        this.setRotateAngle(stomach, -0.6283185307179586F, 0.0F, 0.0F);
        this.tail03Fur = new ModelRenderer(this, 0, 91);
        this.tail03Fur.setRotationPoint(-1.0F, -2.2F, 0.0F);
        this.tail03Fur.addBox(0.0F, 0.0F, 0.0F, 2, 5, 7, 0.0F);
        this.lFoot_1 = new ModelRenderer(this, 0, 68);
        this.lFoot_1.setRotationPoint(0.1F, 1.3F, -0.1F);
        this.lFoot_1.addBox(-1.0F, -1.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(lFoot_1, -1.3439035240356336F, 0.0F, 0.0F);
        this.rPaw = new ModelRenderer(this, 26, 51);
        this.rPaw.setRotationPoint(0.35F, 3.5F, 0.1F);
        this.rPaw.addBox(-1.0F, -0.5F, 0.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(rPaw, -0.9075712110370513F, 0.0F, 0.0F);
        this.chest = new ModelRenderer(this, 0, 14);
        this.chest.setRotationPoint(0.0F, -0.9F, 2.2F);
        this.chest.addBox(-3.0F, -3.0F, -8.0F, 6, 6, 8, 0.0F);
        this.setRotateAngle(chest, 0.767944870877505F, 0.0F, 0.0F);
        this.tail02Fur = new ModelRenderer(this, 0, 91);
        this.tail02Fur.setRotationPoint(-1.0F, -2.0F, 0.0F);
        this.tail02Fur.addBox(0.0F, 0.0F, 0.0F, 2, 5, 7, 0.0F);
        this.lEarFloof = new ModelRenderer(this, 7, 41);
        this.lEarFloof.setRotationPoint(0.0F, -1.8F, 0.0F);
        this.lEarFloof.addBox(0.0F, -3.0F, -1.5F, 0, 5, 3, 0.0F);
        this.upperJaw = new ModelRenderer(this, 0, 38);
        this.upperJaw.setRotationPoint(0.0F, -0.8F, -3.4F);
        this.upperJaw.addBox(-1.5F, -0.4F, -2.0F, 3, 2, 3, 0.0F);
        this.setRotateAngle(upperJaw, 0.22689280275926282F, 0.0F, 0.0F);
        this.lEar = new ModelRenderer(this, 0, 44);
        this.lEar.setRotationPoint(1.5F, -1.2F, -0.7F);
        this.lEar.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(lEar, -0.5410520681182421F, 0.4886921905584123F, 0.2617993877991494F);
        this.fur01 = new ModelRenderer(this, 0, 111);
        this.fur01.setRotationPoint(0.0F, 2.3F, -7.5F);
        this.fur01.addBox(-2.5F, 0.0F, 0.0F, 5, 2, 5, 0.0F);
        this.lFoot = new ModelRenderer(this, 0, 68);
        this.lFoot.mirror = true;
        this.lFoot.setRotationPoint(0.1F, 1.3F, -0.1F);
        this.lFoot.addBox(-1.0F, -1.0F, 0.0F, 2, 4, 1, 0.0F);
        this.setRotateAngle(lFoot, -1.3439035240356336F, 0.0F, 0.0F);
        this.lLeg02 = new ModelRenderer(this, 15, 59);
        this.lLeg02.setRotationPoint(0.0F, 3.2F, -1.7F);
        this.lLeg02.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(lLeg02, 0.8726646259971648F, 0.0F, 0.0F);
        this.rEar = new ModelRenderer(this, 0, 44);
        this.rEar.mirror = true;
        this.rEar.setRotationPoint(-1.5F, -1.2F, -0.7F);
        this.rEar.addBox(-0.5F, -2.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(rEar, -0.5410520681182421F, -0.4886921905584123F, -0.2617993877991494F);
        this.lLeg03a = new ModelRenderer(this, 22, 56);
        this.lLeg03a.setRotationPoint(-0.4F, 3.6F, 0.1F);
        this.lLeg03a.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(lLeg03a, -0.45378560551852565F, 0.02356194490192345F, 0.0F);
        this.rArm02b = new ModelRenderer(this, 9, 51);
        this.rArm02b.mirror = true;
        this.rArm02b.setRotationPoint(0.2F, 0.0F, -1.0F);
        this.rArm02b.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2, 0.0F);
        this.tail03 = new ModelRenderer(this, 17, 73);
        this.tail03.setRotationPoint(0.0F, 0.5F, 5.7F);
        this.tail03.addBox(-1.5F, -1.0F, 0.0F, 3, 3, 7, 0.0F);
        this.setRotateAngle(tail03, 0.6981317007977318F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 13, 38);
        this.snout.setRotationPoint(0.0F, -0.4F, -2.0F);
        this.snout.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(snout, 0.20943951023931953F, 0.0F, 0.0F);
        this.tail04Fur = new ModelRenderer(this, 13, 98);
        this.tail04Fur.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.tail04Fur.addBox(-1.0F, 0.0F, 0.0F, 2, 5, 7, 0.0F);
        this.rEarFloof = new ModelRenderer(this, 7, 41);
        this.rEarFloof.mirror = true;
        this.rEarFloof.setRotationPoint(0.0F, -1.8F, 0.0F);
        this.rEarFloof.addBox(0.0F, -3.0F, -1.5F, 0, 5, 3, 0.0F);
        this.rLeg02 = new ModelRenderer(this, 15, 59);
        this.rLeg02.mirror = true;
        this.rLeg02.setRotationPoint(0.0F, 3.2F, -1.7F);
        this.rLeg02.addBox(-1.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(rLeg02, 0.8726646259971648F, 0.0F, 0.0F);
        this.lLeg01 = new ModelRenderer(this, 0, 59);
        this.lLeg01.setRotationPoint(3.5F, -0.8F, 5.2F);
        this.lLeg01.addBox(-1.5F, 0.0F, -2.5F, 3, 4, 4, 0.0F);
        this.rLeg = new ModelRenderer(this, 0, 59);
        this.rLeg.mirror = true;
        this.rLeg.setRotationPoint(-3.5F, -0.8F, 5.2F);
        this.rLeg.addBox(-1.5F, 0.0F, -2.5F, 3, 4, 4, 0.0F);
        this.lArm01 = new ModelRenderer(this, 0, 51);
        this.lArm01.setRotationPoint(3.0F, 1.4F, -6.2F);
        this.lArm01.addBox(-1.0F, -1.2F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(lArm01, -0.45378560551852565F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 15, 29);
        this.head.setRotationPoint(0.0F, -0.2F, -3.4F);
        this.head.addBox(-2.0F, -2.0F, -3.0F, 4, 4, 3, 0.0F);
        this.setRotateAngle(head, 0.2792526803190927F, 0.0F, 0.0F);
        this.rArm02a = new ModelRenderer(this, 17, 51);
        this.rArm02a.mirror = true;
        this.rArm02a.setRotationPoint(-0.4F, 3.3F, 0.1F);
        this.rArm02a.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 2, 0.0F);
        this.setRotateAngle(rArm02a, -0.3141592653589793F, 0.0F, 0.0F);
        this.lArm02a = new ModelRenderer(this, 17, 51);
        this.lArm02a.setRotationPoint(0.4F, 3.3F, 0.1F);
        this.lArm02a.addBox(-0.5F, 0.0F, -1.0F, 1, 4, 2, 0.0F);
        this.setRotateAngle(lArm02a, -0.3141592653589793F, 0.0F, 0.0F);
        this.tail02 = new ModelRenderer(this, 17, 73);
        this.tail02.setRotationPoint(0.0F, 0.3F, 4.4F);
        this.tail02.addBox(-1.5F, -1.0F, 0.0F, 3, 3, 7, 0.0F);
        this.setRotateAngle(tail02, 1.0471975511965976F, 0.0F, 0.0F);
        this.tail01Fur = new ModelRenderer(this, 13, 85);
        this.tail01Fur.setRotationPoint(-1.5F, -2.5F, 0.0F);
        this.tail01Fur.addBox(0.0F, 0.0F, 0.0F, 3, 5, 6, 0.0F);
        this.lLeg03b = new ModelRenderer(this, 22, 56);
        this.lLeg03b.setRotationPoint(0.3F, 0.0F, 0.0F);
        this.lLeg03b.addBox(0.0F, 0.0F, -1.0F, 1, 2, 2, 0.0F);
        this.rArm01 = new ModelRenderer(this, 0, 51);
        this.rArm01.mirror = true;
        this.rArm01.setRotationPoint(-3.0F, 1.4F, -6.2F);
        this.rArm01.addBox(-1.0F, -1.2F, -1.0F, 2, 5, 2, 0.0F);
        this.setRotateAngle(rArm01, -0.45378560551852565F, 0.0F, 0.0F);
        this.rLeg02b = new ModelRenderer(this, 22, 56);
        this.rLeg02b.mirror = true;
        this.rLeg02b.setRotationPoint(0.3F, 0.0F, 0.0F);
        this.rLeg02b.addBox(0.0F, 0.0F, -1.0F, 1, 2, 2, 0.0F);
        this.neck = new ModelRenderer(this, 0, 29);
        this.neck.setRotationPoint(0.0F, -0.6F, -7.2F);
        this.neck.addBox(-1.5F, -2.0F, -4.0F, 3, 4, 4, 0.0F);
        this.setRotateAngle(neck, -0.3141592653589793F, 0.0F, 0.0F);
        this.lPaw = new ModelRenderer(this, 26, 51);
        this.lPaw.setRotationPoint(-0.35F, 3.1F, 0.5F);
        this.lPaw.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 1, 0.0F);
        this.setRotateAngle(lPaw, -0.9075712110370513F, 0.0F, 0.0F);
        this.lArm02b = new ModelRenderer(this, 9, 51);
        this.lArm02b.setRotationPoint(-1.2F, 0.0F, -1.0F);
        this.lArm02b.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2, 0.0F);
        this.tail01 = new ModelRenderer(this, 1, 69);
        this.tail01.setRotationPoint(0.0F, 0.7F, 5.5F);
        this.tail01.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 6, 0.0F);
        this.setRotateAngle(tail01, 1.3962634015954636F, 0.0F, 0.0F);
        this.lowerJaw = new ModelRenderer(this, 22, 37);
        this.lowerJaw.setRotationPoint(0.0F, 1.3F, -2.7F);
        this.lowerJaw.addBox(-1.0F, -0.5F, -2.0F, 2, 1, 2, 0.0F);
        this.rLeg02a = new ModelRenderer(this, 22, 56);
        this.rLeg02a.mirror = true;
        this.rLeg02a.setRotationPoint(-0.4F, 3.6F, 0.1F);
        this.rLeg02a.addBox(-0.5F, 0.0F, -1.0F, 1, 2, 2, 0.0F);
        this.setRotateAngle(rLeg02a, -0.45378560551852565F, 0.0F, 0.0F);
        this.tail04 = new ModelRenderer(this, 0, 79);
        this.tail04.setRotationPoint(0.0F, -0.1F, 6.2F);
        this.tail04.addBox(-1.5F, -1.0F, 0.0F, 3, 3, 6, 0.0F);
        this.setRotateAngle(tail04, -0.8726646259971648F, 0.0F, 0.0F);
        this.tail03.addChild(this.tail03Fur);
        this.lLeg03b.addChild(this.lFoot_1);
        this.rArm02a.addChild(this.rPaw);
        this.stomach.addChild(this.chest);
        this.tail02.addChild(this.tail02Fur);
        this.lEar.addChild(this.lEarFloof);
        this.head.addChild(this.upperJaw);
        this.head.addChild(this.lEar);
        this.chest.addChild(this.fur01);
        this.rLeg02b.addChild(this.lFoot);
        this.lLeg01.addChild(this.lLeg02);
        this.head.addChild(this.rEar);
        this.lLeg02.addChild(this.lLeg03a);
        this.rArm02a.addChild(this.rArm02b);
        this.tail02.addChild(this.tail03);
        this.upperJaw.addChild(this.snout);
        this.tail04.addChild(this.tail04Fur);
        this.rEar.addChild(this.rEarFloof);
        this.rLeg.addChild(this.rLeg02);
        this.stomach.addChild(this.lLeg01);
        this.stomach.addChild(this.rLeg);
        this.chest.addChild(this.lArm01);
        this.neck.addChild(this.head);
        this.rArm01.addChild(this.rArm02a);
        this.lArm01.addChild(this.lArm02a);
        this.tail01.addChild(this.tail02);
        this.tail01.addChild(this.tail01Fur);
        this.lLeg03a.addChild(this.lLeg03b);
        this.chest.addChild(this.rArm01);
        this.rLeg02a.addChild(this.rLeg02b);
        this.chest.addChild(this.neck);
        this.lArm02a.addChild(this.lPaw);
        this.lArm02a.addChild(this.lArm02b);
        this.stomach.addChild(this.tail01);
        this.head.addChild(this.lowerJaw);
        this.rLeg02.addChild(this.rLeg02a);
        this.tail03.addChild(this.tail04);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.stomach.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}