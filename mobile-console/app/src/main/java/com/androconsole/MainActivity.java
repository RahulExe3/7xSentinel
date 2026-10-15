package com.androconsole;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * AndroConsole: mobile-first adaptation of the legacy desktop console layout.
 *
 * This build is an interface prototype. It deliberately does not open a listener,
 * discover devices, send remote commands, or collect device data.
 */
public class MainActivity extends Activity {
    private final int bg = Color.rgb(14, 18, 25);
    private final int panel = Color.rgb(26, 33, 44);
    private final int panel2 = Color.rgb(35, 44, 58);
    private final int accent = Color.rgb(104, 197, 177);
    private final int primary = Color.rgb(239, 243, 248);
    private final int muted = Color.rgb(163, 175, 191);

    private LinearLayout root;
    private LinearLayout content;
    private String activePage = "Overview";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(bg);
        getWindow().setNavigationBarColor(bg);
        getWindow().getDecorView().setSystemUiVisibility(0);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setBackgroundColor(bg);
        root.setPadding(dp(18), dp(14), dp(18), dp(12));

        root.addView(label("AndroConsole", 27, primary, true));
        TextView subtitle = label("LEGACY CONSOLE · MOBILE EDITION", 10, accent, true);
        subtitle.setPadding(0, dp(3), 0, dp(14));
        root.addView(subtitle);

        root.addView(statusStrip());

        HorizontalScrollView navScroll = new HorizontalScrollView(this);
        navScroll.setHorizontalScrollBarEnabled(false);
        LinearLayout nav = new LinearLayout(this);
        nav.setOrientation(LinearLayout.HORIZONTAL);
        nav.setPadding(0, dp(14), 0, dp(12));
        for (String page : new String[]{"Overview", "Modules", "Event log", "About"}) {
            Button button = new Button(this);
            button.setText(page);
            button.setAllCaps(false);
            button.setTextSize(12);
            button.setTextColor(primary);
            button.setMinHeight(dp(40));
            button.setPadding(dp(12), 0, dp(12), 0);
            button.setBackground(background(activePage.equals(page) ? accent : panel2, 10));
            button.setOnClickListener(v -> showPage(page));
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, dp(40));
            p.rightMargin = dp(8);
            nav.addView(button, p);
        }
        navScroll.addView(nav);
        root.addView(navScroll);

        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        scroll.addView(content);
        root.addView(scroll, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));

        setContentView(root);
        showPage(activePage);
    }

    private View statusStrip() {
        LinearLayout strip = new LinearLayout(this);
        strip.setOrientation(LinearLayout.HORIZONTAL);
        strip.setGravity(Gravity.CENTER_VERTICAL);
        strip.setPadding(dp(12), dp(10), dp(12), dp(10));
        strip.setBackground(background(panel, 12));
        TextView dot = label("●", 12, accent, true);
        strip.addView(dot);
        TextView status = label("  LOCAL PREVIEW  ·  NO SERVER LISTENER", 10, muted, true);
        strip.addView(status, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        return strip;
    }

    private void showPage(String page) {
        activePage = page;
        content.removeAllViews();
        if ("Overview".equals(page)) {
            content.addView(sectionTitle("Console overview", "Legacy desktop sections adapted for a phone-sized screen."));
            content.addView(infoCard("Server", "Stopped / not configured", "This preview does not start a network listener."));
            content.addView(infoCard("Connected clients", "0 sessions", "No device discovery or remote connection is active."));
            content.addView(infoCard("Activity log", "Waiting for local events", "Only interface navigation is recorded in this prototype."));
            content.addView(infoCard("Compatibility", "Legacy protocol not connected", "A genuine mobile port needs a separate, secure protocol and permission review."));
        } else if ("Modules".equals(page)) {
            content.addView(sectionTitle("Legacy console modules", "Navigation preview only. Selecting a module will not query a device."));
            String[][] modules = {
                {"Home / device information", "Basic session and device-status view"},
                {"Picture viewer", "Image panel layout"},
                {"File tree viewer", "Directory-list panel layout"},
                {"Call logs", "Call-history panel layout"},
                {"Contacts", "Contact-list panel layout"},
                {"SMS viewer", "Message-list panel layout"},
                {"Map viewer", "Location panel layout"},
                {"Sound listener", "Audio panel layout"},
                {"Video player", "Video panel layout"},
                {"Call monitor", "Call-event panel layout"},
                {"SMS monitor", "Message-event panel layout"},
                {"Preferences", "Connection and UI settings"}
            };
            for (String[] module : modules) {
                content.addView(infoCard(module[0], "PREVIEW ONLY", module[1] + ". No data is collected or commands sent."));
            }
        } else if ("Event log".equals(page)) {
            content.addView(sectionTitle("Event log", "Local interface events only."));
            content.addView(infoCard("Application started", "Local event", "AndroConsole interface initialised."));
            content.addView(infoCard("Remote transport", "Inactive", "No client sockets, device discovery, or command channel are running."));
        } else {
            content.addView(sectionTitle("About AndroConsole", "Android companion interface for studying the legacy desktop console."));
            content.addView(infoCard("Source reference", "AndroRAT legacy project", "Desktop GUI source remains in src/AndroratServer/."));
            content.addView(infoCard("Build scope", "Java / native Android Views", "This prototype has no Kotlin application source and no third-party UI libraries."));
            content.addView(infoCard("Safety", "Transparent lab prototype", "Remote surveillance and device-control actions are intentionally not implemented."));
        }
    }

    private View sectionTitle(String title, String detail) {
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(0, dp(8), 0, dp(10));
        box.addView(label(title, 20, primary, true));
        TextView sub = label(detail, 12, muted, false);
        sub.setPadding(0, dp(5), 0, 0);
        box.addView(sub);
        return box;
    }

    private View infoCard(String title, String status, String detail) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(15), dp(14), dp(15), dp(14));
        card.setBackground(background(panel, 12));

        card.addView(label(title, 15, primary, true));
        TextView state = label(status, 11, accent, true);
        state.setPadding(0, dp(7), 0, dp(5));
        card.addView(state);
        card.addView(label(detail, 12, muted, false));

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.bottomMargin = dp(10);
        card.setLayoutParams(p);
        return card;
    }

    private TextView label(String value, int size, int color, boolean bold) {
        TextView view = new TextView(this);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color);
        if (bold) view.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        view.setLineSpacing(dp(2), 1.0f);
        return view;
    }

    private GradientDrawable background(int color, int radius) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(dp(radius));
        return drawable;
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }
}
