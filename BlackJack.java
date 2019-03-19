import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BlackJack {
	private static final String CARTA_BASE = "+------+ ";
	private static final String CARTA_ALTEZZA = "|      | ";
	private static final String CARTA_SIMBOLO = "|  simbolo  | ";
	private final static String WINNER = "CROUPIER: \"AND THE WINNER IS: ";
	private static String PALYER;
	private final static Map<String, Integer> scores = new HashMap<>();

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		int posizione = 0;
		int dealerWin = 0;
		int playerWin = 0;
		List<String> mazzo = new ArrayList<String>(getScores().keySet());
		List<String> dealerMano = new ArrayList<>();
		System.out.println("\n♠ ♣ ♦ ♥ BLACK JACK ♥ ♦ ♣ ♠\n"); Thread.sleep(1000);
		System.out.print("CROUPIER: \"COME TI CHIAMI?\" ");
        Scanner scanner = new Scanner(System. in);
        String nome = scanner. nextLine();
        System.out.print("\n\"CIAO "+ nome.toUpperCase() + ", "); Thread.sleep(1500);
        System.out.print("BENEVENUTO NEL MIO CASINO' E BUONA FORTUNA!\"\n");
        setPALYER(nome.toUpperCase());
		Collections.shuffle(mazzo);
		boolean continuos = true;
		while (continuos) {
			if (posizione < mazzo.size()) {
				dealerMano.add(mazzo.get(posizione));
				posizione ++;
			} else {
				posizione = 0;
				Collections.shuffle(mazzo);
				System.out.println("\nCROUPIER: \"PERDONAMI " + getPALYER() + ", DEVO MESCOLARE LE CARTE!\"\n");
				Thread.sleep(2000);
				dealerMano.add(mazzo.get(posizione));
				posizione ++;
			}
			if (posizione < mazzo.size()) {
				dealerMano.add(mazzo.get(posizione));
				posizione ++;
			} else {
				posizione = 0;
				Collections.shuffle(mazzo);
				System.out.println("\nCROUPIER: \"PERDONAMI " + getPALYER() + ", DEVO MESCOLARE LE CARTE!\"\n");
				Thread.sleep(2000);
				dealerMano.add(mazzo.get(posizione));
				posizione ++;
			}
			int dealerCount = getScores().get(dealerMano.get(0)) + getScores().get(dealerMano.get(1));
			boolean dealerBlackJack = dealerCount == 21;
			List<String> mano = new ArrayList<>();
			if (posizione < mazzo.size()) {
				mano.add(mazzo.get(posizione));
				posizione ++;
			} else {
				posizione = 0;
				Collections.shuffle(mazzo);
				System.out.println("\nCROUPIER: \"PERDONAMI " + getPALYER() + ", DEVO MESCOLARE LE CARTE!\"\n");
				Thread.sleep(2000);
				mano.add(mazzo.get(posizione));
				posizione ++;
			}
			if (posizione < mazzo.size()) {
				mano.add(mazzo.get(posizione));
				posizione ++;
			} else {
				posizione = 0;
				Collections.shuffle(mazzo);
				System.out.println("\nCROUPIER: \"PERDONAMI " + getPALYER() + ", DEVO MESCOLARE LE CARTE!\"\n");
				Thread.sleep(2000);
				mano.add(mazzo.get(posizione));
				posizione ++;
			}
			Thread.sleep(2000);
			int playerCount = getScores().get(mano.get(0)) + getScores().get(mano.get(1));
			boolean playerBlackJack = playerCount == 21;
			System.out.println("\nIL CROUPIER STA DISTRIBUENDO LE CARTE \n"); Thread.sleep(2000);
			System.out.println("CROUPIER: \n"); stampCard(dealerMano); Thread.sleep(2000);
			System.out.println(getPALYER() + ": \n"); stampCard(mano); Thread.sleep(2000);
			boolean again = playerCount < 21;
			while (again) {
				if (playerCount > 21) {
					again = false;
				} else {
					System.out.println("CROUPIER: \"" + getPALYER() + ", DESIDERI PESCARE UN'ALTRA CARTA? (Y/N)\"");
					System.out.print(getPALYER() + ": ");
					String question = scanner. nextLine();
					switch (question.toUpperCase()) {
					case "Y" :
						again = true;
						if (posizione < mazzo.size()) {
							mano.add(mazzo.get(posizione));
							posizione ++;
						} else {
							posizione = 0;
							Collections.shuffle(mazzo);
							System.out.println("\nCROUPIER: \"PERDONAMI " + getPALYER() + ", DEVO MESCOLARE LE CARTE!\"\n");
							Thread.sleep(2000);
							mano.add(mazzo.get(posizione));
							posizione ++;
						}
						playerCount += getScores().get(mano.get(mano.size() - 1));
						Thread.sleep(2000);
						System.out.println("\nCROUPIER: \n"); stampCard(dealerMano); Thread.sleep(2000);
						System.out.println(getPALYER() + ": \n"); stampCard(mano);
						break;
					case "N":
						again = false;
						System.out.println();
						break;
					default:
						again = false;
						System.out.println();
						break;
					}
				}
				Thread.sleep(2000);
			}
			while (dealerCount < playerCount && playerCount <= 21) {
				if (posizione < mazzo.size()) {
					dealerMano.add(mazzo.get(posizione));
					posizione ++;
				} else {
					posizione = 0;
					Collections.shuffle(mazzo);
					System.out.println("\nCROUPIER: \"PERDONAMI " + getPALYER() + ", DEVO MESCOLARE LE CARTE!\"\n");
					Thread.sleep(2000);
					dealerMano.add(mazzo.get(posizione));
					posizione ++;
				}
				dealerCount += getScores().get(dealerMano.get(dealerMano.size() - 1));
				System.out.println("\nCROUPIER: \n"); stampCard(dealerMano); Thread.sleep(2000);
				System.out.print(getPALYER() + ": \n"); stampCard(mano); Thread.sleep(2000);
			}
			System.out.println("TOTAL SCORES:"); Thread.sleep(1500);
			System.out.println("CROUPIER -> " + dealerCount); Thread.sleep(1500);
			System.out.println(getPALYER() + " -> " + playerCount + "\n"); Thread.sleep(1500);
			System.out.print(WINNER);
			String winner = evaluateWinner(dealerCount, playerCount, dealerBlackJack, playerBlackJack, playerWin, dealerWin);
			System.out.println(winner + "!\"\n"); Thread.sleep(1500);
			System.out.println("CROUPIER: \"" + getPALYER() + ", DESIDERI GIOCARE UN'ALTRA PARTITA? (Y/N)\"");
			System.out.print(getPALYER() + ": ");
			String question = scanner. nextLine();
			if (winner.equalsIgnoreCase(getPALYER())) {
				playerWin ++;
			} else {
				dealerWin ++;
			}
			continuos = question.toUpperCase().equalsIgnoreCase("N") ? false : true;
			dealerCount = 0;
			playerCount = 0;
			dealerMano = new ArrayList<>();
			Thread.sleep(2000);
		}
		System.out.println("\nTOTAL MATCHES WON: "); Thread.sleep(1500);
		System.out.println("CROUPIER -> " + dealerWin); Thread.sleep(1500);
		System.out.println(getPALYER() + " -> " + playerWin);
	}

	private static String evaluateWinner(int dealer, int player, boolean dealerB, boolean playerB, int playerWin, int dealerWin) throws InterruptedException {
		String winner = "";
		if (dealer == player) {
			winner = "CROUPIER";
			dealerWin ++;
		} else if (dealerB && playerB) {
			winner = "CROUPIER";
			dealerWin ++;
		} else if (dealerB && !playerB) {
			winner = "CROUPIER";
			dealerWin ++;
		} else if (playerB && !dealerB) {
			winner = getPALYER();
		} else if (dealer == 21) {
			winner = "CROUPIER";
			dealerWin ++;
		} else if (player == 21) {
			winner = getPALYER();
			playerWin ++;
		} else if (dealer > 21 && player <= 21) {
			winner = getPALYER();
			playerWin ++;
		} else if (player > 21 && dealer <= 21) {
			winner = "CROUPIER";
			dealerWin ++;
		} else if (dealer < 21 && player < 21 && dealer > player) {
			winner = "CROUPIER";
			dealerWin ++;
		} else if (dealer < 21 && player < 21 && dealer < player) {
			winner = getPALYER();
			playerWin ++;
		} else {
			winner = "CROUPIER";
			dealerWin ++;
		}
		Thread.sleep(5000);
		return winner;
	}

	public static Map<String, Integer> getScores() {
		if (scores.isEmpty()) {
			scores.put("A ♠", 11);
			scores.put("1 ♠", 1);
			scores.put("2 ♠", 2);
			scores.put("3 ♠", 3);
			scores.put("4 ♠", 4);
			scores.put("5 ♠", 5);
			scores.put("6 ♠", 6);
			scores.put("7 ♠", 7);
			scores.put("8 ♠", 8);
			scores.put("9 ♠", 9);
			scores.put("10 ♠", 10);
			scores.put("J ♠", 10);
			scores.put("Q ♠", 10);
			scores.put("K ♠", 10);
			scores.put("A ♦", 11);
			scores.put("1 ♦", 1);
			scores.put("2 ♦", 2);
			scores.put("3 ♦", 3);
			scores.put("4 ♦", 4);
			scores.put("5 ♦", 5);
			scores.put("6 ♦", 6);
			scores.put("7 ♦", 7);
			scores.put("8 ♦", 8);
			scores.put("9 ♦", 9);
			scores.put("10 ♦", 10);
			scores.put("J ♦", 10);
			scores.put("Q ♦", 10);
			scores.put("K ♦", 10);
			scores.put("A ♥", 11);
			scores.put("1 ♥", 1);
			scores.put("2 ♥", 2);
			scores.put("3 ♥", 3);
			scores.put("4 ♥", 4);
			scores.put("5 ♥", 5);
			scores.put("6 ♥", 6);
			scores.put("7 ♥", 7);
			scores.put("8 ♥", 8);
			scores.put("9 ♥", 9);
			scores.put("10 ♥", 10);
			scores.put("J ♥", 10);
			scores.put("Q ♥", 10);
			scores.put("K ♥", 10);
			scores.put("A ♣", 11);
			scores.put("1 ♣", 1);
			scores.put("2 ♣", 2);
			scores.put("3 ♣", 3);
			scores.put("4 ♣", 4);
			scores.put("5 ♣", 5);
			scores.put("6 ♣", 6);
			scores.put("7 ♣", 7);
			scores.put("8 ♣", 8);
			scores.put("9 ♣", 9);
			scores.put("10 ♣", 10);
			scores.put("J ♣", 10);
			scores.put("Q ♣", 10);
			scores.put("K ♣", 10);
		}
		return scores;
	}
	
	private static void stampCard(List<String> mano) {
		for (int i = 0; i < mano.size(); i ++) {			
			System.out.print(CARTA_BASE);
		}
		System.out.println();
		for (int i = 0; i < mano.size(); i ++) {
			String s = mano.get(i).split(" ")[0];
			System.out.print(CARTA_SIMBOLO.replaceAll("simbolo", s.length() > 1 ? s : s + " " ));
		}
		System.out.println();
		for (int i = 0; i < mano.size(); i ++) {			
			System.out.print(CARTA_ALTEZZA);
		}
		System.out.println();
		for (int i = 0; i < mano.size(); i ++) {			
			System.out.print(CARTA_ALTEZZA);
		}
		System.out.println();
		for (int i = 0; i < mano.size(); i ++) {
			String s = mano.get(i).split(" ")[1];
			System.out.print(CARTA_SIMBOLO.replaceAll("simbolo", s + " " ));
		}
		System.out.println();
		for (int i = 0; i < mano.size(); i ++) {			
			System.out.print(CARTA_BASE);
		}
		System.out.println("\n");
	}
	
	public static String getPALYER() {
		return PALYER;
	}
	
	public static void setPALYER(String pALYER) {
		PALYER = pALYER;
	}
}
