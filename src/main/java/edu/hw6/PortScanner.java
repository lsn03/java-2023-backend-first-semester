package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class PortScanner {

    public static final int FTP_PORT = 21;
    public static final int SSH_PORT = 22;
    public static final int EPMAP_PORT = 135;
    public static final int NET_BIOS_NAMING_SERVICE_PORT = 137;
    public static final int NET_BIOS_DATAGRAM_SERVICE_PORT = 138;
    public static final int NET_BIOS_SEANCE_SERVICE_PORT = 139;
    public static final int MICROSOFT_ACTIVE_DIRECTORY_PORT = 445;
    public static final int MYSQL_PORT = 3306;
    public static final int POSTGRES_SQL_PORT = 5432;
    public static final int WEB_SERVICE_DYNAMIC_DISCOVERY = 3702;
    public static final int MULTICAST_DNS_PORT = 5353;
    public static final int LLMNR_PORT = 5355;

    private static final int AVAILABLE_PORTS = 49151;

    public static final String BUSY = "\tЗанят\t";


    public String getPortsInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int port = 0; port <= AVAILABLE_PORTS; port++) {
            try (ServerSocket serverSocket = new ServerSocket(port);) {
                serverSocket.close();
            } catch (IOException e) {

                stringBuilder.append("TCP\t")
                        .append(port)
                        .append(BUSY)
                        .append(getCommonService(port))
                        .append(System.lineSeparator());

            }

            try (DatagramSocket datagramSocket = new DatagramSocket(port);) {
                datagramSocket.close();
            } catch (IOException e) {
                stringBuilder.append("UDP\t")
                        .append(port)
                        .append(BUSY)
                        .append(getCommonService(port))
                        .append(System.lineSeparator());
            }
        }
        return stringBuilder.toString();
    }

    private static String getCommonService(int port) {
        return switch (port) {
            case FTP_PORT -> "FTP";
            case SSH_PORT -> "SSH";
            case EPMAP_PORT -> "EPMAP";
            case NET_BIOS_NAMING_SERVICE_PORT -> "Служба имен NetBIOS";
            case NET_BIOS_DATAGRAM_SERVICE_PORT -> "Служба датаграмм NetBIOS";
            case NET_BIOS_SEANCE_SERVICE_PORT -> "Служба сеансов NetBIOS";
            case MICROSOFT_ACTIVE_DIRECTORY_PORT -> "Microsoft-DS Active Directory";
            case MYSQL_PORT -> "MYSQL";
            case WEB_SERVICE_DYNAMIC_DISCOVERY -> "Динамическое обнаружение веб-служб";
            case MULTICAST_DNS_PORT -> "Многоадресный DNS";
            case LLMNR_PORT -> "Link-Local Multicast Name Resolution (LLMNR)";
            case POSTGRES_SQL_PORT -> "PostgreSQL";
            default -> "";
        };

    }

}




