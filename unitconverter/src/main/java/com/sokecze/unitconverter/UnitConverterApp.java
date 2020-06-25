package com.sokecze.unitconverter;

import com.sokecze.core.annotations.Page;
import com.sokecze.unitconverter.pages.AdPage;
import com.sokecze.unitconverter.pages.AreaPage;
import com.sokecze.unitconverter.pages.TemperaturePage;
import com.sokecze.unitconverter.pages.VolumePage;

public class UnitConverterApp {
    @Page(activityName = "com.google.android.gms.ads.AdActivity")
    public static AdPage adPage;

    @Page(activityName = "com.jeremyfeinstein.slidingmenu.example.fragments.FragmentChangeActivity")
    public static AreaPage areaPage;

    @Page
    public static VolumePage volumePage;

    @Page
    public static TemperaturePage temperaturePage;
}
