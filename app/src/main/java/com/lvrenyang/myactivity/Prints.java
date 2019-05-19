package com.lvrenyang.myactivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;

import com.lvrenyang.io.Canvas;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Prints {

	public static boolean PrintTicket(Context ctx, Canvas canvas, int nPrintWidth, int nPrintHeight) {
		boolean bPrintResult = false;
				
		Bitmap logo = getImageFromAssetsFile(ctx, "Kitty.bmp");

		Typeface tfTitle = Typeface.createFromAsset(ctx.getAssets(), "fonts" + File.separator + "wenquanyi.ttf");
		Typeface tfContent = Typeface.createFromAsset(ctx.getAssets(), "fonts" + File.separator + "simsun.ttc");
		Typeface tfNumber = Typeface.createFromAsset(ctx.getAssets(), "fonts" + File.separator + "xichang.otf");

		canvas.CanvasBegin(nPrintWidth, nPrintHeight);
		canvas.SetPrintDirection(0);
		
		canvas.DrawBox(0,0,nPrintWidth-1,nPrintHeight-1);

		canvas.DrawBitmap(logo, 1, 10, 0);

		canvas.DrawText("中国福利彩票", 10 + logo.getWidth(), 10, 0, tfTitle, 40, 0);
		canvas.DrawText("销售期	2015033", 0, 60, 0, tfContent, 20, 0);
		canvas.DrawText("兑奖期	2015033", nPrintWidth/2, 60, 0, tfContent, 20, 0);
		canvas.DrawText("站号	230902001", 0, 80, 0, tfContent, 20, 0);
		canvas.DrawText("7639-A", nPrintWidth/2, 80, 0, tfContent, 20, 0);
		canvas.DrawText("注数		5", 0, 100, 0, tfContent, 20, 0);
		canvas.DrawText("金额	10.00", nPrintWidth/2, 100, 0, tfContent, 20, 0);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 120, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 160, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 200, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 240, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 280, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 320, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		
		canvas.DrawBarcode("179736629632968", 0, 380, 0, 2, 80, Canvas.BARCODE_TYPE_CODE128);
		canvas.DrawQRCode("179736629632968", Canvas.HORIZONTALALIGNMENT_RIGHT, 380, 0, 3, 5, 1);
		
		canvas.DrawText("玩法 七乐彩 单式*1 开奖时间2016-09-30", 0, 500, 0, tfContent, 20, 0);
		canvas.DrawText("销售时间  20160923 15:10:47", 0, 520, 0, tfContent, 20, 0);
		canvas.DrawText("地址  福建厦门", 0, 540, 0, tfContent, 20, 0);
		canvas.DrawText("自助站点通兑	客服热线：0771-5787586", 0, 560, 0, tfContent, 20, 0);

		canvas.CanvasEnd();
		canvas.CanvasPrint(1, 1);

		bPrintResult = canvas.GetIO().IsOpened();
		return bPrintResult;
	}

	public static boolean PrintTicket2(Context ctx, Canvas canvas, int nPrintWidth, int nPrintHeight) {
		boolean bPrintResult = false;

		Bitmap logo = getImageFromAssetsFile(ctx, "Kitty.bmp");

		Typeface tfTitle = Typeface.createFromAsset(ctx.getAssets(), "fonts" + File.separator + "NanumBarunGothic.ttf");
		Typeface tfContent = Typeface.createFromAsset(ctx.getAssets(), "fonts" + File.separator + "NanumGothic.ttf");
		Typeface tfNumber = Typeface.createFromAsset(ctx.getAssets(), "fonts" + File.separator + "xichang.otf");

		canvas.CanvasBegin(nPrintWidth, nPrintHeight);
		canvas.SetPrintDirection(0);

		canvas.DrawBox(0,0,nPrintWidth-1,nPrintHeight-1);

		canvas.DrawBitmap(logo, 1, 10, 0);

		canvas.DrawText("소프트웨어에 물들다", 10 + logo.getWidth(), 10, 0, tfTitle, 40, 0);
		canvas.DrawText("출력일  1234567", 0, 60, 0, tfContent, 20, 0);
		canvas.DrawText("행사일  4567890", nPrintWidth/2, 60, 0, tfContent, 20, 0);
		canvas.DrawText("출력일  1234567", 0, 80, 0, tfContent, 20, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("행사일  4567890", nPrintWidth/2, 80, 0, tfContent, 20, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("注数		5", 0, 100, 0, tfContent, 20, 0);
		canvas.DrawText("金额	10.00", nPrintWidth/2, 100, 0, tfContent, 20, 0);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 120, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 160, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 200, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 240, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 280, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);
		canvas.DrawText("A: 02 07 10 17 20 21 25", 0, 320, 0, tfNumber, 40, Canvas.FONTSTYLE_BOLD);

		canvas.DrawBarcode("179736629632968", 0, 380, 0, 2, 80, Canvas.BARCODE_TYPE_CODE128);
		canvas.DrawQRCode("179736629632968", Canvas.HORIZONTALALIGNMENT_RIGHT, 380, 0, 3, 5, 1);

		canvas.DrawText("玩法 七乐彩 单式*1 开奖时间2016-09-30", 0, 500, 0, tfContent, 20, 0);
		canvas.DrawText("销售时间  20160923 15:10:47", 0, 520, 0, tfContent, 20, 0);
		canvas.DrawText("地址  福建厦门", 0, 540, 0, tfContent, 20, 0);
		canvas.DrawText("自助站点通兑	客服热线：0771-5787586", 0, 560, 0, tfContent, 20, 0);

		canvas.CanvasEnd();
		canvas.CanvasPrint(1, 1);

		bPrintResult = canvas.GetIO().IsOpened();
		return bPrintResult;
	}

	public static boolean PrintTest(Context ctx, Canvas canvas, int nPrintWidth, int nPrintHeight) {
		boolean bPrintResult = false;
		
		int w = 384;
		int h = 800;
		
		{// 测试打印方向
			Bitmap logo = getImageFromAssetsFile(ctx, "arrow.png");
			int dirTbl[] = {Canvas.DIRECTION_LEFT_TO_RIGHT,Canvas.DIRECTION_BOTTOM_TO_TOP,Canvas.DIRECTION_RIGHT_TO_LEFT,Canvas.DIRECTION_TOP_TO_BOTTOM};
			
			for(int dirIdx = 0; dirIdx < dirTbl.length; ++dirIdx)
			{
				canvas.CanvasBegin(w, h);
				canvas.DrawText("测试打印方向", 0, 0, 0, Typeface.DEFAULT, 20, 0);
				canvas.SetPrintDirection(dirTbl[dirIdx]);
				
				canvas.DrawBox(0,0,w-1,h-1);

				canvas.DrawBitmap(logo, 1, 10, 0);

				canvas.CanvasEnd();
				canvas.CanvasPrint(1, 1);
			}
		}
		{// 测试对齐方式
			Bitmap logo = getImageFromAssetsFile(ctx, "arrow.png");
			int dirTbl[] = {Canvas.DIRECTION_LEFT_TO_RIGHT,Canvas.DIRECTION_BOTTOM_TO_TOP,Canvas.DIRECTION_RIGHT_TO_LEFT,Canvas.DIRECTION_TOP_TO_BOTTOM};
			int hAlignTbl[] = {Canvas.HORIZONTALALIGNMENT_LEFT,Canvas.HORIZONTALALIGNMENT_CENTER,Canvas.HORIZONTALALIGNMENT_RIGHT};
			int vAlignTbl[] = {Canvas.VERTICALALIGNMENT_TOP,Canvas.VERTICALALIGNMENT_CENTER,Canvas.VERTICALALIGNMENT_BOTTOM};
			
			for(int dirIdx = 0; dirIdx < dirTbl.length; ++dirIdx)
			{
				canvas.CanvasBegin(w, h);
				canvas.DrawText("测试对齐方式", 0, 0, 0, Typeface.DEFAULT, 20, 0);
				canvas.SetPrintDirection(dirTbl[dirIdx]);
				
				canvas.DrawBox(0,0,w-1,h-1);

				for(int hAlignIdx = 0; hAlignIdx < hAlignTbl.length; ++hAlignIdx)
				{
					for(int vAlignIdx = 0; vAlignIdx < vAlignTbl.length; ++vAlignIdx)
					{
						canvas.DrawBitmap(logo, hAlignTbl[hAlignIdx], vAlignTbl[vAlignIdx], 0);
					}
				}

				canvas.CanvasEnd();
				canvas.CanvasPrint(1, 1);
			}
		}
		{// 测试旋转角度
			Bitmap logo = getImageFromAssetsFile(ctx, "arrow.png");
			int dirTbl[] = {Canvas.DIRECTION_LEFT_TO_RIGHT,Canvas.DIRECTION_BOTTOM_TO_TOP,Canvas.DIRECTION_RIGHT_TO_LEFT,Canvas.DIRECTION_TOP_TO_BOTTOM};
			int hAlignTbl[] = {Canvas.HORIZONTALALIGNMENT_LEFT,Canvas.HORIZONTALALIGNMENT_CENTER,Canvas.HORIZONTALALIGNMENT_RIGHT};
			int vAlignTbl[] = {Canvas.VERTICALALIGNMENT_TOP,Canvas.VERTICALALIGNMENT_CENTER,Canvas.VERTICALALIGNMENT_BOTTOM};
			int rotTbl[] = {0,45,90,135,180,225,270,315};
			
			for(int dirIdx = 0; dirIdx < dirTbl.length; ++dirIdx)
			{
				for(int rotIdx = 0; rotIdx < rotTbl.length; ++rotIdx)
				{
					canvas.CanvasBegin(w, h);
					canvas.DrawText("测试旋转角度：" + rotTbl[rotIdx], 0, 0, 0, Typeface.DEFAULT, 20, 0);
					canvas.SetPrintDirection(dirTbl[dirIdx]);
					
					canvas.DrawBox(0,0,w-1,h-1);

					for(int hAlignIdx = 0; hAlignIdx < hAlignTbl.length; ++hAlignIdx)
					{
						for(int vAlignIdx = 0; vAlignIdx < vAlignTbl.length; ++vAlignIdx)
						{
							canvas.DrawBitmap(logo, hAlignTbl[hAlignIdx], vAlignTbl[vAlignIdx], rotTbl[rotIdx]);
						}
					}

					canvas.CanvasEnd();
					canvas.CanvasPrint(1, 1);
				}
			}
		}
		
		
		bPrintResult = canvas.GetIO().IsOpened();
		return bPrintResult;
	}

	/**
	 * 从Assets中读取图片
	 */
	public static Bitmap getImageFromAssetsFile(Context ctx, String fileName) {
		Bitmap image = null;
		AssetManager am = ctx.getResources().getAssets();
		try {
			InputStream is = am.open(fileName);
			image = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;
	}
	
	public static Bitmap resizeImage(Bitmap bitmap, int w, int h) {
		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);

		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
				height, matrix, true);

		// make a Drawable from Bitmap to allow to set the Bitmap
		// to the ImageView, ImageButton or what ever
		return resizedBitmap;
	}
}
