package com.ststudy.client.android.utils.glide.load.data;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Fetches an {@link java.io.InputStream} for a local {@link android.net.Uri}.
 */
public class StreamLocalUriFetcher extends LocalUriFetcher<InputStream> {
  public StreamLocalUriFetcher(Context context, Uri uri) {
    super(context, uri);
  }

  @Override
  protected InputStream loadResource(Uri uri, ContentResolver contentResolver)
      throws FileNotFoundException {
    InputStream inputStream = contentResolver.openInputStream(uri);
    if (inputStream == null) {
      throw new FileNotFoundException("InputStream is null for :" + uri);
    }
    return inputStream;
  }

  @Override
  protected void close(InputStream data) throws IOException {
    data.close();
  }

  @Override
  public Class<InputStream> getDataClass() {
    return InputStream.class;
  }
}
