package com.sevenx.console

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private val bg = Color.rgb(10, 15, 24)
    private val panel = Color.rgb(20, 29, 43)
    private val accent = Color.rgb(54, 220, 190)
    private val muted = Color.rgb(160, 177, 195)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg
        window.decorView.setBackgroundColor(bg)

        val page = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(22), dp(28), dp(22), dp(24))
            setBackgroundColor(bg)
        }
        val scroll = ScrollView(this).apply { addView(page) }

        page.addView(label("7xConsole", 30, Color.WHITE, true))
        page.addView(label("MOBILE LAB DASHBOARD", 11, accent, true).also {
            it.setPadding(0, dp(4), 0, dp(22))
        })
        page.addView(card("Workspace status", "Local demo mode", "No devices connected"))
        page.addView(label("OVERVIEW", 12, muted, true).also {
            it.setPadding(0, dp(24), 0, dp(10))
        })
        page.addView(card("Connection monitor", "Not connected", "Connection controls are not configured in this demo."))
        page.addView(card("Activity log", "No events yet", "This panel is reserved for transparent, user-authorised lab activity."))
        page.addView(card("Configuration", "Local settings", "Build and interface information for this installation."))
        page.addView(label("7xSentinel • Companion UI • v1.0.0", 11, muted, false).also {
            it.gravity = Gravity.CENTER
            it.setPadding(0, dp(26), 0, dp(4))
        })
        setContentView(scroll)
    }

    private fun card(title: String, status: String, detail: String): LinearLayout {
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(16), dp(16), dp(16), dp(16))
            setBackgroundColor(panel)
        }
        box.addView(label(title, 17, Color.WHITE, true))
        box.addView(label(status, 13, accent, true).also {
            it.setPadding(0, dp(9), 0, dp(6))
        })
        box.addView(label(detail, 13, muted, false))
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.bottomMargin = dp(12)
        box.layoutParams = params
        return box
    }

    private fun label(text: String, size: Int, color: Int, bold: Boolean): TextView =
        TextView(this).apply {
            this.text = text
            textSize = size.toFloat()
            setTextColor(color)
            if (bold) typeface = Typeface.DEFAULT_BOLD
            setLineSpacing(dp(2).toFloat(), 1f)
        }

    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}
