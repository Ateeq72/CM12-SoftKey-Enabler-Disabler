package com.ahmed.ateeq.cm12sofkeyenabler;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import eu.chainfire.libsuperuser.Shell;

/**
 * Implementation of App Widget functionality.
 */
public class CM12AppWidget_ateeq extends AppWidgetProvider {

    private static final String ENABLE    = "enable";
    private static final String DISABLE    = "disable";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Get all ids
        ComponentName thisWidget = new ComponentName(context, CM12AppWidget_ateeq.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        for (int widgetId : allWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.cm12_app_widget_ateeq);

            remoteViews.setOnClickPendingIntent(R.id.ewbutton, getPendingSelfIntent(context, ENABLE));
            remoteViews.setOnClickPendingIntent(R.id.dwbutton, getPendingSelfIntent(context, DISABLE));


            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);//add this line
        if (ENABLE.equals(intent.getAction())) {
            // your onClick action is here
            Shell.SU.run("settings put secure dev_force_show_navbar 1");
            Toast.makeText(context, "SoftKeys Enabled!. ", Toast.LENGTH_SHORT).show();
            }
        else if (DISABLE.equals(intent.getAction())) {
            Shell.SU.run("settings put secure dev_force_show_navbar 0");
            Toast.makeText(context, "SoftKeys Disabled!. ", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onEnabled(Context context)
    {
        Toast.makeText(context, "Please Enable/Disable directly from SoftKey App atleast once! ", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

               // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.cm12_app_widget_ateeq);
                // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }


}


