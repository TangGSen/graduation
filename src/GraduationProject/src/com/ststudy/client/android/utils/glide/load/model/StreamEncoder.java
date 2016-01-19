package com.ststudy.client.android.utils.glide.load.model;

import android.util.Log;

import com.ststudy.client.android.utils.glide.load.Encoder;
import com.ststudy.client.android.utils.glide.load.Options;
import com.ststudy.client.android.utils.glide.load.engine.bitmap_recycle.ByteArrayPool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * An {@link com.ststudy.client.android.utils.glide.load.Encoder} that can write an {@link java.io.InputStream} to
 * disk.
 */
public class StreamEncoder implements Encoder<InputStream> {
  private static final String TAG = "StreamEncoder";
  private final ByteArrayPool byteArrayPool;

  public StreamEncoder(ByteArrayPool byteArrayPool) {
    this.byteArrayPool = byteArrayPool;
  }

  @Override
  public boolean encode(InputStream data, File file, Options options) {
    byte[] buffer = byteArrayPool.get(ByteArrayPool.STANDARD_BUFFER_SIZE_BYTES);
    boolean success = false;
    OutputStream os = null;
    try {
      os = new FileOutputStream(file);
      int read;
      while ((read = data.read(buffer)) != -1) {
        os.write(buffer, 0, read);
      }
      os.close();
      success = true;
    } catch (IOException e) {
      if (Log.isLoggable(TAG, Log.DEBUG)) {
        Log.d(TAG, "Failed to encode data onto the OutputStream", e);
      }
    } finally {
      if (os != null) {
        try {
          os.close();
        } catch (IOException e) {
          // Do nothing.
        }
      }
      byteArrayPool.put(buffer);
    }
    return success;
  }
}