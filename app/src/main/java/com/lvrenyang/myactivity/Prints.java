package com.lvrenyang.myactivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;

import com.lvrenyang.io.Canvas;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class Prints {
	private final static int clBlack = 0xFF000000;
	private final static int clWhite = 0xFFFFFFFF;

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

    public static boolean PrintBitmap(Context ctx, Canvas canvas, int nPrintWidth, int nPrintHeight, Bitmap bitmap) {
        boolean bPrintResult = false;


        int bw = bitmap.getWidth();
        int bh = bitmap.getHeight();
        Bitmap pic;
        if (bw > nPrintWidth) {
        	pic = resizeImage(bitmap, nPrintWidth, (bh > nPrintHeight) ? bh : nPrintHeight);
		}
        else {
        	pic = bitmap;
		}
        // white balance
		Bitmap balPic = balance(pic, pic);
        // dithering
		Bitmap bwBitmap = com.askjeffreyliu.floydsteinbergdithering.Utils.floydSteinbergDithering(balPic);

        canvas.CanvasBegin(pic.getWidth(), pic.getHeight());
        canvas.SetPrintDirection(0);
        canvas.DrawBitmap(bwBitmap, 0, 0, 0);

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
	
	public static Bitmap resizeImage(Bitmap image, int maxWidth, int maxHeight) {
		int width = image.getWidth();
		int height = image.getHeight();
		float ratioBitmap = (float) width / (float) height;
		float ratioMax = (float) maxWidth / (float) maxHeight;

		int finalWidth = maxWidth;
		int finalHeight = maxHeight;
		if (ratioMax > ratioBitmap) {
			finalWidth = (int) ((float)maxHeight * ratioBitmap);
		} else {
			finalHeight = (int) ((float)maxWidth / ratioBitmap);
		}
		image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
		return image;
	}
	/**
	 * Calculates the median RGB color in an image over all the channels
	 * @param img The image to find the median colour in
	 * @return the median colour from the image
	 */
	private static int getMedianColor(Bitmap img){
		int pointCount = img.getWidth() * img.getHeight();

		int[] rgbs = new int[pointCount];
		int[] reds = new int[pointCount];
		int[] greens = new int[pointCount];
		int[] blues = new int[pointCount];

		img.getPixels(rgbs,0,img.getWidth(),0,0,img.getWidth(),img.getHeight());
//		img.getRGB(0, 0, img.getWidth(), img.getHeight(), rgbs, 0, img.getWidth());
		for(int i = 0;i < rgbs.length;i ++){
			int rgb = rgbs[i];
			reds[i]   = (rgb >> 16) & 0xFF;
			greens[i] = (rgb >> 8) & 0xFF;
			blues[i]  = (rgb >> 0) & 0xFF;
		}

		Arrays.sort(reds);
		Arrays.sort(greens);
		Arrays.sort(blues);

		//Find the average of the inter quartile range
		int r = 0, g = 0, b = 0;
		int lowerQuartile = (int) Math.floor(pointCount / 4.0);
		int upperQuartile = (int) Math.floor(3.0 * (pointCount / 4.0));

		int quartileRange = upperQuartile - lowerQuartile + 1;
		for (int i = lowerQuartile; i <= upperQuartile; i++){
			r += reds[i];
			g += greens[i];
			b += blues[i];
		}

		return Color.rgb(r / quartileRange, g / quartileRange, b / quartileRange);
	}


	/**
	 * Returns a (hopefully) white balanced version of the given image, assuming that the colour
	 * in the given rectangles is supposed to be white
	 * @param fullImage the image to process
	 * @param whiteSpot the supposedly white spots of the image
	 * @return the white-balanced image
	 */
	public static Bitmap balance(Bitmap fullImage, Bitmap whiteSpot){
		System.out.printf("Image Dimensions: %dx%d\n", fullImage.getWidth(), fullImage.getHeight());

		int whiteMedian = getMedianColor(whiteSpot);
		//Calculate the white corrected value
		double weightedRed = Color.red(whiteMedian);
		double weightedGreen = Color.green(whiteMedian);
		double weightedBlue = Color.blue(whiteMedian);

		int[] fullRGB = new int[fullImage.getWidth() * fullImage.getHeight()];

		fullImage.getPixels(fullRGB, 0, fullImage.getWidth(), 0, 0, fullImage.getWidth(), fullImage.getHeight());

		for(int y = 0;y < fullImage.getHeight();y ++){
			for(int x = 0;x < fullImage.getWidth();x ++){
				int index = y * fullImage.getWidth() + x;
				int oldRed   = (fullRGB[index] >> 16) & 0xFF;
				int oldGreen = (fullRGB[index] >> 8) & 0xFF;
				int oldBlue  = (fullRGB[index] >> 0) & 0xFF;
				int newRed   = (int) Math.min(oldRed * (255. / weightedRed), 255);
				int newGreen = (int) Math.min(oldGreen * (255. / weightedGreen), 255);
				int newBlue   = (int) Math.min(oldBlue * (255. / weightedBlue), 255);
				int newCol = (0xFF << 24) | (newRed << 16) | (newGreen << 8) | newBlue;
				fullRGB[index] = newCol;
			}
		}
		Bitmap newImg = Bitmap.createBitmap(fullImage);
		newImg.setPixels(fullRGB,0,fullImage.getWidth(),0,0,fullImage.getWidth(),fullImage.getHeight());

		return newImg;
	}

	public static boolean PrintDots(Context ctx, Canvas canvas, int nPrintWidth, int nPrintHeight) {
		boolean bPrintResult = false;

		int printHeight = 105 + 20;

		int[] fullRGB = new int[nPrintWidth * printHeight];

		//fullImage.getPixels(fullRGB, 0, fullImage.getWidth(), 0, 0, fullImage.getWidth(), fullImage.getHeight());

		for(int y = 0;y < printHeight;y ++){
			for(int x = 0;x < nPrintWidth;x ++){
				int index = y * nPrintWidth + x;

				int newCol;
				if (y >= 0+10 && y < 20+10) {
					// 0 ~ 20 (20) : 50% / 1010101010
					newCol = ((x + y) % 2) == 0 ? clBlack : clWhite;
				} else if (y >= 25+10 && y < 45+10) {
					// 25 ~ 45 (20) : 50% / 110011001100
					newCol = (((y / 2) % 2 == 0) && ((x / 2) % 2 == 0)) ? clBlack : clWhite;
				} else if (y >= 50+10 && y < 70+10) {
					// 50 ~ 70 (20) : 한 줄씩
					newCol = (y % 2 == 0)  ? clBlack : clWhite;
				} else if (y >= 75+10 && y < 95+10) {
					// 75 ~ 95 (20) : 2 줄씩
					newCol = ((y / 2) % 2 == 0)  ? clBlack : clWhite;
				} else {
					newCol = clWhite;
				}
				fullRGB[index] = newCol;
			}
		}
		Bitmap pic = Bitmap.createBitmap(nPrintWidth, printHeight, Bitmap.Config.ARGB_8888);
		//Bitmap newImg = Bitmap.createBitmap(fullImage);
		pic.setPixels(fullRGB,0,nPrintWidth,0,0,nPrintWidth, printHeight);


		canvas.CanvasBegin(pic.getWidth(), pic.getHeight());
		canvas.SetPrintDirection(0);
		canvas.DrawBitmap(pic, 0, 0, 0);

		canvas.CanvasEnd();
		//canvas.CanvasPrint(1, 1);
		canvas.CanvasPrint(0, 0);

		bPrintResult = canvas.GetIO().IsOpened();
		return bPrintResult;
	}

	public static boolean PrintFeed(Context ctx, Canvas canvas, int nPrintWidth, int nPrintHeight) {

		boolean bPrintResult = false;

		int printHeight = 10;

		int[] fullRGB = new int[nPrintWidth * printHeight];

		for(int y = 0;y < printHeight;y ++){
			for(int x = 0;x < nPrintWidth;x ++){
				int index = y * nPrintWidth + x;
				fullRGB[index] = clWhite;
			}
		}
		Bitmap pic = Bitmap.createBitmap(nPrintWidth, printHeight, Bitmap.Config.ARGB_8888);
		pic.setPixels(fullRGB,0,nPrintWidth,0,0,nPrintWidth, printHeight);

		canvas.CanvasBegin(pic.getWidth(), pic.getHeight());
		canvas.SetPrintDirection(0);
		canvas.DrawBitmap(pic, 0, 0, 0);

		canvas.CanvasEnd();
		canvas.CanvasPrint(1, 1);

		bPrintResult = canvas.GetIO().IsOpened();
		return bPrintResult;
	}
}
