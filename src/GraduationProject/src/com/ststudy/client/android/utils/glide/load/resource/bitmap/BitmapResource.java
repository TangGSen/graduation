package com.ststudy.client.android.utils.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.ststudy.client.android.utils.glide.load.engine.Resource;
import com.ststudy.client.android.utils.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ststudy.client.android.utils.glide.util.Preconditions;
import com.ststudy.client.android.utils.glide.util.Util;

/**
 * A resource wrapping a {@link android.graphics.Bitmap} object.
 */
public class BitmapResource implements Resource<Bitmap> {
  private final Bitmap bitmap;
  private final BitmapPool bitmapPool;

  /**
   * Returns a new {@link BitmapResource} wrapping the given {@link Bitmap} if the Bitmap is
   * non-null or null if the given Bitmap is null.
   *
   * @param bitmap     A Bitmap.
   * @param bitmapPool A non-null {@link com.ststudy.client.android.utils.glide.load.engine.bitmap_recycle.BitmapPool}.
   */
  @Nullable
  public static BitmapResource obtain(@Nullable Bitmap bitmap, BitmapPool bitmapPool) {
    if (bitmap == null) {
      return null;
    } else {
      return new BitmapResource(bitmap, bitmapPool);
    }
  }

  public BitmapResource(Bitmap bitmap, BitmapPool bitmapPool) {
    this.bitmap = Preconditions.checkNotNull(bitmap, "Bitmap must not be null");
    this.bitmapPool = Preconditions.checkNotNull(bitmapPool, "BitmapPool must not be null");
  }

  @Override
  public Class<Bitmap> getResourceClass() {
    return Bitmap.class;
  }

  @Override
  public Bitmap get() {
    return bitmap;
  }

  @Override
  public int getSize() {
    return Util.getBitmapByteSize(bitmap);
  }

  @Override
  public void recycle() {
    bitmapPool.put(bitmap);
  }
}
