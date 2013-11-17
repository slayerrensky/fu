package fu.krypt.exercise1;

import haufigkeit.Bigramme;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		String plainText =  "MKKBPNRBPVNNKGBMKKMFUOKFBVTOFQZD" + 
							"DPPSZKOHNYHOBFELUEURGFGRKORLMRVK" +
							"FSFKOANYHOPHNUFIBZWKGMKKLFOJPEGO" +
							"JCAFTNHSIAVOUZQYHOIXFKAAREKWTUGV" +
							"LTMZZEARMNHTDHGAGRYOAAFJBECNKOPF" +
							"JQUZTDRTOCSKAHPJKKBPNDCOFSNUAPCY" +
							"AQIXFNSGCPXSXUYABPNYJBFTKGASBZKZ" +
							"AXEINPFAWKBBNVLPDRDHZNDBLLNZAYIM" +
							"KKPLNRZDPFAGFNURWZFAKGGTSOANMXZX" +
							"SRBZVSCKRVKFSZDKHRKQOTOZOMFMNBZM" +
							"RXWSGCTKSCN";
		
		String plainText2 =  "DEFENDTHEEASTWALLOFTHECASTLE";
		
		final String key = "FORTIFICATION";
		

		String cipher = BeaufortCipher.crypt(plainText2, key);
		System.out.println(cipher);
		String plain = BeaufortCipher.decrypt(cipher, key);
		System.out.println(plain);
	}

}
