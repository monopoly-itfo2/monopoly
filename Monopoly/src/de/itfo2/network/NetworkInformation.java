package de.itfo2.network;

import java.net.Inet4Address;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
/**
 * Diese Klasse liefert statische Methoden um Informationen über die Netzwerkumgebung zu bekommen.<br><br>
 * Das Format einer IP-Adresse ist eine Integer-Array mit 4 Elementen: <br>
 * <b>Beispiel:</b> 192.168.1.1 == {192,168,1,1}  <br><b>also:</b> adresse[2] == 168
 * 
 * @author Marco Ernst
 *
 */
public final class NetworkInformation {
	public static int[] getBroadcast(int[] localhost, int[] subnetmask) {
		int[] broadcast = new int[4];
		int[] notSubnetmask = subnetmask.clone();
		for (int i = 0; i < 4; i++) {
			String result = Integer.toBinaryString(notSubnetmask[i]);
			int length = result.length();
			if (length < 8) {
				for (int j = 0; j < (8 - length); j++) {
					result = 0 + result;
				}
			}
			result = result.replace('1', '2');
			result = result.replace('0', '1');
			result = result.replace('2', '0');
			notSubnetmask[i] = Integer.parseInt(result, 2);
			broadcast[i] = localhost[i] | notSubnetmask[i];
		}
		return broadcast;
	}

	public static int[] getNetwork(int[] localhost, int[] subnetmask) {
		int[] network = new int[4];
		for (int i = 0; i < 4; i++) {
			network[i] = localhost[i] & subnetmask[i];
		}
		return network;
	}

	public static int[] getSubnetmask() throws SocketException, UnknownHostException {
		int[] subnetmask = new int[4];
		NetworkInterface networkInterface = NetworkInterface
				.getByInetAddress(Inet4Address.getLocalHost());
		int masklenght = networkInterface.getInterfaceAddresses().get(0)
				.getNetworkPrefixLength();
		if( masklenght < 1 || masklenght > 31){
			masklenght = 24;
		}
		String part = "";
		for (int i = 1; i <= 32; i++) {
			if (masklenght - i >= 0) {
				part += 1;
			} else {
				part += 0;
			}
			if (i % 8 == 0) {
				subnetmask[(i / 8) - 1] = Integer.valueOf(part, 2);
				part = "";
			}
		}
		return subnetmask;
	}

	public static int[] getLocalhost() throws UnknownHostException {
		int[] localhost = new int[4];
		String[] hostStr = Inet4Address.getLocalHost().getHostAddress()
				.split("\\.");
		for (int i = 0; i < 4; i++) {
			localhost[i] = Integer.valueOf(hostStr[i]);
		}
		return localhost;
	}
}
