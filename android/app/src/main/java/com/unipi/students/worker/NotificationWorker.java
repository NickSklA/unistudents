package io.ionic.starter;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import io.ionic.starter.R;

public  class NotificationWorker extends Worker {

  public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
    super(context, workerParams);
  }
  @NonNull
  @Override
  public Result doWork(){
    Context context = getApplicationContext();

    try {
      String CHANNEL_ID = "notification";

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        CharSequence name = context.getString(R.string.channel_name);
        String description = context.getString(R.string.channel_name);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
        context.startActivity(intent);

        String textTitle = "Important notification";
        String textContent = "Your class starts soon";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
          .setSmallIcon(R.drawable.ic_action_name)
          .setContentTitle(textTitle)
          .setContentText(textContent)
          .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notificationManager.notify(0, builder.build());
      }

      return Result.success();
    } catch (Throwable throwable){
      Log.e("ERROR", "Error for the notification", throwable);
      return Result.failure();
    }

  }
}


