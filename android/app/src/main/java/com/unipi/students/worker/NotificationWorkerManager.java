package io.ionic.starter;
import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import java.util.concurrent.TimeUnit;

public class NotificationWorkerManager {

  public static void enqueueNotificationWork() {
    Constraints constraints = new Constraints.Builder()
      .setRequiresCharging(true)
      .build();

    OneTimeWorkRequest notificationWork = new OneTimeWorkRequest.Builder(NotificationWorker.class)
      .setConstraints(constraints)
      .setInitialDelay(10, TimeUnit.MINUTES) // Example initial delay
      .addTag("notification_work") // Optional tag for identifying work
      .build();

    WorkManager.getInstance().enqueue(notificationWork);
  }
}
