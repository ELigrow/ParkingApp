package edu.wctc.eligrow;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * @author eplig
 * @version 1.0
 */
public class ATM {

    /**
     * Reads CSV file and converts it to ArrayList. Returns said ArrayList.
     * @param fileName String: Name of file
     * @return ArrayList<Ticket>: ArrayList of Ticket objects to be used later in the program.
     */
    public ArrayList<Ticket> ReadPrevReport(String fileName) {
        ArrayList<Ticket> totalTickets = new ArrayList<>();
        FileInput prevReport;
        String line;
        try {
            prevReport = new FileInput(fileName);
            while ((line = prevReport.fileReadLine()) != null) {
                String[] toBeConverted = line.split(",");
                if (!toBeConverted[0].equalsIgnoreCase("true")) {
                    int inTime = Integer.parseInt(toBeConverted[0]);
                    int outTime = Integer.parseInt(toBeConverted[1]);
                    int cost = Integer.parseInt(toBeConverted[2]);
                    Ticket ticket = new Ticket(inTime, outTime, cost);
                    totalTickets.add(ticket);
                } else {
                    Ticket ticket = new Ticket(true);
                    totalTickets.add(ticket);
                }
            }
            prevReport.fileClose();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalTickets;
    }

    /**
     * Adds to file and ArrayList based on user action
     * @param al ArrayList<Ticket>: ArrayList to add to for program.
     * @param fileName String: Name of file
     */
    public void CheckIn(ArrayList<Ticket> al, String fileName) {
        Scanner keyboard = new Scanner(System.in);
        Random checkIn = new Random();
        Random checkOut = new Random();
        boolean goodInput = false;
        int subAns;
        try {

            do {
                FileOutput out = new FileOutput(fileName);
                int inTime = checkIn.nextInt((12 - 7) + 1) + 7;
                System.out.print(" Best Value Parking Garage\n =========================\n 1) Check out\n " +
                        "2) Lost Ticket\n=>");
                subAns = keyboard.nextInt();
                if (subAns == 1) {
                    int outTime = checkOut.nextInt((23 - 13) + 1) + 13;
                    Ticket newTicket = new Ticket(inTime, outTime);
                    if (inTime < 12) {
                        System.out.println(" Best Value Parking Garage\n =========================\n Receipt for vehicle id " +
                                (al.size() + 1) + "\n " + (outTime - inTime) + " hours parked  " + inTime + " am - " +
                                (outTime - 12) + " pm\n $" + newTicket.cost);
                    } else {
                        System.out.println(" Best Value Parking Garage\n =========================\n Receipt for vehicle id" +
                                (al.size() + 1) + "\n " + (outTime - inTime) + " hours parked " + inTime + " pm - " +
                                (outTime - 12) + " pm\n $" + newTicket.cost);
                    }
                    al.add(newTicket);
                    String str = newTicket.Write();
                    out.fileWrite(str);
                    System.out.print("Press enter to continue:");
                    keyboard.nextLine();
                    keyboard.nextLine();
                    out.fileClose();
                    goodInput = true;
                } else if (subAns == 2) {
                    Ticket newTicket = new Ticket(true);
                    System.out.println(" Best Value Parking Garage\n =========================\n Receipt for vehicle id " +
                            (al.size() + 1) + "\n Lost Ticket" + "\n $" + newTicket.cost);
                    al.add(newTicket);
                    String str = newTicket.Write();
                    out.fileWrite(str);
                    System.out.print("Press enter to continue:");
                    keyboard.nextLine();
                    keyboard.nextLine();
                    out.fileClose();
                    goodInput = true;
                } else {
                    System.out.println("This is an invalid input. Please enter a valid input.");
                }
            } while (!goodInput);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Summarizes ArrayList into a report.
     * @param al ArrayList<Ticket>: ArrayList to read from.
     */
    public void CloseGarage(ArrayList<Ticket> al){
        int grandTotal = 0;
        int lostCost = 0;
        int keptTicketCost = 0;
        int keptTickets = 0;
        int lostTickets = 0;

        for (Ticket tally : al) {
            if (tally.cost == 25) {
                lostTickets++;
                lostCost += tally.cost;
            } else {
                keptTicketCost += tally.cost;
                keptTickets++;
            }
            grandTotal += tally.cost;
        }

        System.out.print(" Best Value Parking Garage\n =========================\n Activity to date\n\n $" +
                keptTicketCost + " was collected from " + keptTickets + " check-ins\n $" + lostCost +
                " was collected from " + lostTickets + " lost tickets\n\n $" + grandTotal + " was collected " +
                "overall.\n\n Press enter to close the garage.");
    }
    }
