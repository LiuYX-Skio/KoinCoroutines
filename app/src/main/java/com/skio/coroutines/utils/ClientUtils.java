package com.skio.coroutines.utils;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 客户端信息获取工具类
 *
 * @author zhou
 *
 */
public class ClientUtils {
	/**
	 * 获取当前应用程序的版本号
	 *
	 * @return 版本号
	 */
	public static String getAppVersion(Context context) {
		// 获取手机的包管理者
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(),
					0);
			return packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 获取当前应用程序的版本号
	 *
	 * @return 版本号
	 */
	public static int getAppVersionCode(Context context) {
		// 获取手机的包管理者
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(),
					0);
			return packInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public static String getTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());

	}


	/**
	 * 获取当前应用名称
	 *
	 * @param context
	 * @return
	 */
	public static String getAppName(Context context) {
		PackageManager pm = context.getPackageManager();
		try {
			PackageInfo packInfo = pm.getPackageInfo(context.getPackageName(),
					0);
			String appName = packInfo.applicationInfo.loadLabel(pm).toString();
			return appName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}
	/**
	 * 跳转到拨号界面
	 *
	 * @param context
	 * @param phoneNumber
	 */
	public static void call(Context context, String phoneNumber) {
		//跳转到拨号界面，同时传递电话号码
		try {
			Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
			context.startActivity(dialIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取imei号
	 *
	 * @param context
	 * @return
	 */
	@SuppressLint("MissingPermission")
	public static String getIMEI(Context context) {
		TelephonyManager mTelephonyMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return mTelephonyMgr.getDeviceId();
	}

	/**
	 * 获取电话号(有可能为空)
	 *
	 * @param context
	 * @return
	 */
	public static String getPhone(Context context) {
		TelephonyManager mTelephonyMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		@SuppressLint("MissingPermission") String num = mTelephonyMgr.getLine1Number();
		if (TextUtils.isEmpty(num)) {
			return "";
		} else {
			return num;
		}
	}

	/**
	 * 获取手机制造商
	 *
	 * @return
	 */
	public static String getManufacturers() {
		return android.os.Build.MANUFACTURER;
	}

	/**
	 * 获取手机品牌
	 *
	 * @return
	 */
	public static String getBrand() {
		return android.os.Build.BRAND;
	}

	/**
	 * 获取手机型号
	 *
	 * @return
	 */
	public static String getModel() {
		return android.os.Build.MODEL;
	}

	/**
	 * 获取手机操作系统
	 *
	 * @return
	 */
	public static String getOs() {
		return "Android " + android.os.Build.VERSION.INCREMENTAL;
	}

	/**
	 * 获取操作系统版本
	 *
	 * @return
	 */
	public static String getOsVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 获得屏幕分辨率x(宽)
	 *
	 * @return
	 */
	public static int getScreenX(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 获得屏幕分辨率y(高)
	 *
	 * @return
	 */
	public static int getScreenY(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * 是否有蓝牙
	 *
	 * @return
	 */
	public static boolean getBluetooth() {
		BluetoothAdapter bluetoothadapter = BluetoothAdapter
				.getDefaultAdapter();
		if (bluetoothadapter == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 是否有摄像头
	 *
	 * @return
	 */
	public static boolean getCamera() {
		// boolean has = true;
		// try {
		//
		// Camera camera = android.hardware.Camera.open();
		// camera.stopPreview();
		// camera.release();
		// } catch (RuntimeException e) {
		// e.printStackTrace();
		// has = false;
		// }
		// return has;
		return true;
	}

	/**
	 * 连接状态-暂时取不到
	 *
	 * @return
	 */
	public static String getConnecttype() {
		return "";
	}

	/**
	 * CPU最大频率
	 *
	 * @return
	 */
	public static String getMaxCpuFreq() {
		String result = "";
		ProcessBuilder cmd;
		try {
			String[] args = { "/system/bin/cat",
					"/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" };
			cmd = new ProcessBuilder(args);
			Process process = cmd.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[24];
			while (in.read(re) != -1) {
				result = result + new String(re);
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			result = "N/A";
		}
		return result.trim();
	}

	/**
	 * 获取CPU名字
	 */
	@SuppressWarnings("resource")
	public static String getCpuName() {
		String[] array = null;
		try {
			FileReader fr = new FileReader("/proc/cpuinfo");
			BufferedReader br = new BufferedReader(fr);
			String text = br.readLine();
			if(text!=null){
				array = text.split(":\\s+", 2);
				for (int i = 0; i < array.length; i++) {
				}
				return array[1];
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * MAC地址
	 *
	 * @return
	 */
	public static String getMacPath(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);

		@SuppressLint("MissingPermission") WifiInfo info = wifi.getConnectionInfo();

		return info.getMacAddress();
	}

	/**
	 * 内存空间
	 *
	 * @return
	 */
	public static String totalRAM(Context context) {
		/*
		 * String fileName = "/proc/meminfo"; String txt; try { txt =
		 * FileUtil.read(fileName); } catch (IOException e) { Auto-generated
		 * catch block e.printStackTrace(); return 0l; } String[] lines =
		 * txt.split(StringPool.RETURN_NEW_LINE); if(lines.length < 1) return
		 * 0l; String[] line = lines[0].split(":"); if(line.length < 2) return
		 * 0l; String total = line[1]; return Long.valueOf(total);
		 */
		String str1 = "/proc/meminfo";
		String str2;
		String[] arrayOfString = null;
		long initial_memory = 0;
		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(
					localFileReader, 8192);
			str2 = localBufferedReader.readLine();
			if(str2!=null){
				arrayOfString = str2.split("\\s+");
				for (String num : arrayOfString) {
					Log.i(str2, num + "\t");
					initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
					localBufferedReader.close();
				}
			}

		} catch (IOException e) {

		}
		return Formatter.formatFileSize(context, initial_memory);
	}

	/**
	 * 网络制式
	 *
	 * @param context
	 * @return
	 */
	public static String getNetType(Context context) {
		TelephonyManager mTelephonyMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String result = "UNKNOWN";
		switch (mTelephonyMgr.getNetworkType()) {
		case TelephonyManager.NETWORK_TYPE_CDMA:
			result = "CDMA";
			break;
		case TelephonyManager.NETWORK_TYPE_EDGE:
			result = "EDGE";
			break;
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
			result = "EVDO_0";
			break;
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
			result = "EVDO_A";
			break;
		case TelephonyManager.NETWORK_TYPE_GPRS:
			result = "GPRS";
			break;
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			result = "HSDPA";
			break;
		case TelephonyManager.NETWORK_TYPE_HSPA:
			result = "HSPA";
			break;
		case TelephonyManager.NETWORK_TYPE_IDEN:
			result = "IDEN";
			break;
		case TelephonyManager.NETWORK_TYPE_UMTS:
			result = "UMTS";
			break;
		}
		return result;
	}

	/**
	 * 手机制式
	 *
	 * @param context
	 * @return
	 */
	public static String phoneType(Context context) {
		TelephonyManager mTelephonyMgr = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String type = "";
		switch (mTelephonyMgr.getPhoneType()) {
		case TelephonyManager.PHONE_TYPE_NONE:
			type = "NONE";
			break;
		case TelephonyManager.PHONE_TYPE_CDMA:
			type = "CDMA";
			break;
		case TelephonyManager.PHONE_TYPE_GSM:
			type = "GSM";
			break;
		}
		return type;
	}

	/**
	 * SD卡可用大小(MB)
	 */
	public static String availableBlock(Context context) {
		String path = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		if (path == null) {
			return "0";
		}
		StatFs statfs = new StatFs(path);
		// 获取可供程序使用的Block的数量
		@SuppressWarnings("deprecation")
		long nAvailaBlock = statfs.getAvailableBlocks();
		// 获取SDCard上每个block的SIZE
		@SuppressWarnings("deprecation")
		long nBlocSize = statfs.getBlockSize();

		return Formatter.formatFileSize(context, nAvailaBlock * nBlocSize);
	}

	/**
	 * 内部空间可用大小(MB)
	 */
	public static String availableRom(Context context) {
		File root = Environment.getDataDirectory();
		StatFs sf = new StatFs(root.getPath());
		@SuppressWarnings("deprecation")
		long blockSize = sf.getBlockSize();
		@SuppressWarnings("deprecation")
		long availCount = sf.getAvailableBlocks();

		return Formatter.formatFileSize(context, blockSize * availCount);
	}
}

