package CreateShip;

public class Interior {

	public static String [][][] Layout(int w,int h, boolean[][][] s, int roomMax, int roomMin, int shipMin, int shipMax){
		ShipRandom rand= new ShipRandom(null);
		int f=s.length;
		boolean stairsCan=false;
		boolean[] stairs=new boolean[4];//(bot up) (mid up) (mid down) (top down)
		String [][][] shipOut= new String [f][(w*2)+2][(h*2)+2];
		for(int floor=0; floor<f; floor++) {
			for(int x=0; x<(w*2)+2;x++) {
				for(int y=0; y<(h*2)+2; y++) {
					shipOut[floor][x][y]="n";
				}
			}
		}
		int temp=0;
		for(int floor=0; floor<f; floor++) {
			for(int x=0; x<w;x++) {
				for(int y=0; y<h; y++) {
					if(s[floor][x][y]){
						shipOut[floor][x*2][y*2]="ship";
						shipOut[floor][(x*2)+1][(y*2)]="ship";
						shipOut[floor][(x*2)+2][(y*2)]="ship";
						shipOut[floor][(x*2)][(y*2)+1]="ship";
						
						shipOut[floor][(x*2)+1][(y*2)+1]="floor";
						
						shipOut[floor][(x*2)+2][(y*2)+1]="ship";
						shipOut[floor][(x*2)+2][(y*2)+2]="ship";
						shipOut[floor][(x*2)+1][(y*2)+2]="ship";
						shipOut[floor][(x*2)][(y*2)+2]="ship";
						
						temp++;
					}
				}
			}
		}
		if(temp>shipMax||shipMin>temp) {
			return null;
		}
		int cf=(int)(rand.nextDouble()*2)+1;
		int cy=0;
		for(cy=1; shipOut[cf][shipOut[0].length/2-2][cy]!="floor"; cy+=2);
		boolean done=false;
		int posx, posy, posf,sx=0,sy=0,sf=0;
		while(!done) {
			sx=(int)(rand.nextDouble()*w);
			sy=(int)(rand.nextDouble()*h);
			sf=(int)(rand.nextDouble()*3);
			if(s[sf][sx][sy]) {
				done=true;
			}
		}
		posx=sx;
		posy=sy;
		posf=sf;
		shipOut[sf][(posx)*2+1][(posy)*2+1]="d";
		done=false;
		int[] last=new int[(h*w*f)*3];
		int i=0;
		while(!done) {
			boolean can=false;
			if(posy!=0) {
				if(shipOut[posf][(posx)*2+1][(posy-1)*2+1].equals("floor")) {
					can=true;
				}
			}
			if(posy!=h-1) {
				if(shipOut[posf][(posx)*2+1][(posy+1)*2+1].equals("floor")) {
					can=true;
				}
			}
			if(posx!=0) {
				if(shipOut[posf][(posx-1)*2+1][(posy)*2+1].equals("floor")) {
					can=true;
				}
			}
			if(posx!=w-1) {
				if(shipOut[posf][(posx+1)*2+1][(posy)*2+1].equals("floor")) {
					can=true;
				}
			}
			if(!can) {
				if(posf!=0&&!stairs[(posf+1)]) {
					if(shipOut[posf-1][(posx)*2+1][(posy)*2+1].equals("floor")&&shipOut[posf][(posx)*2+1][(posy)*2+1].equals("d")&&!((cf<=posf)&&(posy*2+1-cy)<=2&&(posy*2+1-cy)>=0&&posx*2+1-(shipOut[0].length/2-2)>=0&&posx*2+1-(shipOut[0].length/2-2)<=2)) {
						can=true;
						stairsCan=true;
					}
				}
				if(posf!=f-1&&!stairs[(posf)]) {
					if(shipOut[posf+1][(posx)*2+1][(posy)*2+1].equals("floor")&&shipOut[posf][(posx)*2+1][(posy)*2+1].equals("d")&&!((cf>=posf)&&(posy*2+1-cy)<=2&&(posy*2+1-cy)>=0&&posx*2+1-(shipOut[0].length/2-2)>=0&&posx*2+1-(shipOut[0].length/2-2)<=2)) {
						can=true;
						stairsCan=true;
					}
				}
			}
			if (can) {
				int nx=0, ny=0, nf=0;
				can=false;
				while(!can) {
					int r=(int)(rand.nextDouble()*16);
					if(posy!=0 && r<4) {
						nx=0;
						ny=-1;
						nf=0;
					}else if(posy!=w-1 && r<8) {
						nx=0;
						ny=1;
						nf=0;
					}else if(posx!=0 && r<12) {
						nx=-1;
						ny=0;
						nf=0;
					}else if(posx!=w-1 && r<16) {
						nx=1;
						ny=0;
						nf=0;
					}if(shipOut[posf+nf][(posx+nx)*2+1][(posy+ny)*2+1].equals("floor")) {
						can=true;
					}else {
						for(int st=0;st<2;st++) {
							if(posf!=0&& st==0 &&!stairs[(posf+1)]&&shipOut[posf-1][(posx)*2+1][(posy)*2+1].equals("floor")&&shipOut[posf][(posx)*2+1][(posy)*2+1].equals("d")&&!((cf<=posf)&&(posy*2+1-cy)<=2&&(posy*2+1-cy)>=0&&posx*2+1-(shipOut[0].length/2-2)>=0&&posx*2+1-(shipOut[0].length/2-2)<=2)&&stairsCan) {
								nx=0;
								ny=0;
								nf=-1;
								if(posf==1) {
									stairs[2]=true;
									stairs[0]=true;
								}else {
									stairs[3]=true;
									stairs[1]=true;
								}
								stairsCan=false;
							}else if(posf!=f-1&& st==1&&!stairs[(posf)]&&shipOut[posf+1][(posx)*2+1][(posy)*2+1].equals("floor")&&shipOut[posf][(posx)*2+1][(posy)*2+1].equals("d")&&!((cf>=posf)&&(posy*2+1-cy)<=2&&(posy*2+1-cy)>=0&&posx*2+1-(shipOut[0].length/2-2)>=0&&posx*2+1-(shipOut[0].length/2-2)<=2)&&stairsCan) {
								nx=0;
								ny=0;
								nf=+1;
								if(posf==1) {
									stairs[3]=true;
									stairs[1]=true;
								}else {
									stairs[0]=true;
									stairs[2]=true;
								}
								stairsCan=false;
							}
							if(shipOut[posf+nf][(posx+nx)*2+1][(posy+ny)*2+1].equals("floor")) {
								can=true;
							}
						}
					}
				}
				shipOut=move(posx, posy, posf, shipOut, nx, ny, nf);
				last[i*3]=posy;
				last[i*3+1]=posx;
				last[i*3+2]=posf;
				posy+=ny;
				posx+=nx;
				posf+=nf;
				i++;
			}else {
				posy=last[i*3-3];
				posx=last[i*3-2];
				posf=last[i*3-1];
				i--;
			}
			if(i==0) {
				can=false;
				if(posy!=0) {
					if(shipOut[posf][(posx)*2+1][(posy-1)*2+1].equals("floor")) {
						can=true;
					}
				}
				if(posy!=h-1) {
					if(shipOut[posf][(posx)*2+1][(posy+1)*2+1].equals("floor")) {
						can=true;
					}
				}
				if(posx!=0) {
					if(shipOut[posf][(posx-1)*2+1][(posy)*2+1].equals("floor")) {
						can=true;
					}
				}
				if(posx!=w-1) {
					if(shipOut[posf][(posx+1)*2+1][(posy)*2+1].equals("floor")) {
						can=true;
					}
				}
				if(posf!=0) {
					if(shipOut[posf-1][(posx)*2+1][(posy)*2+1].equals("floor")&&!stairs[(posf+1)]&&shipOut[posf][(posx)*2+1][(posy)*2+1].equals("d")&&!((cf<=posf)&&(posy*2+1-cy)<=2&&(posy*2+1-cy)>=0&&posx*2+1-(shipOut[0].length/2-2)>=0&&posx*2+1-(shipOut[0].length/2-2)<=2)&&stairsCan) {
						can=true;
					}
				}
				if(posf!=f-1) {
					if(shipOut[posf+1][(posx)*2+1][(posy)*2+1].equals("floor")&&!stairs[(posf)]&&shipOut[posf][(posx)*2+1][(posy)*2+1].equals("d")&&!((cf>=posf)&&(posy*2+1-cy)<=2&&(posy*2+1-cy)>=0&&posx*2+1-(shipOut[0].length/2-2)>=0&&posx*2+1-(shipOut[0].length/2-2)<=2)&&stairsCan) {
						can=true;
					}
				}
				if(!can) {
					done=true;
				}
			}
		}
		for(int floor=0; floor<f;floor++) {
			for(int x=0; x<shipOut[0].length;x++) {
				for(int y=0; y<shipOut[0][0].length; y++) {
					if(shipOut[floor][x][y].equals("d")){
						shipOut[floor][x][y]="floor";
					}
				}
			}
		}
		int r=0;
		shipOut=makeRoom(shipOut, shipOut[0].length/2-2, cy, cf, r);
		for(r=1; r<roomMax ;r++) {
			int[] roomsAv=checkRoom(shipOut);
			if (roomsAv[0]!=-1) {
				int indx=3*(int)(rand.nextDouble()*(roomsAv.length/3-1));
				shipOut=makeRoom(shipOut, roomsAv[indx+1], roomsAv[indx], roomsAv[indx+2], r);
			}else {
				break;
			}
		}
		
		for(int floor=0; floor<f;floor++) {
			for(int x=0; x<shipOut[0].length;x++) {
				for(int y=0; y<shipOut[0][0].length; y++) {
					if(shipOut[floor][x][y].equals("up")||shipOut[floor][x][y].equals("down")){
						if(!shipOut[floor][x+1][y].equals("ship")&&!shipOut[floor][x-1][y].equals("ship")) {
							return null;
						}
						if(!shipOut[floor][x+1][y].equals("ship")&&!shipOut[floor][x][y+1].equals("ship")) {
							return null;
						}
						if(!shipOut[floor][x+1][y].equals("ship")&&!shipOut[floor][x][y-1].equals("ship")) {
							return null;
						}
						if(!shipOut[floor][x-1][y].equals("ship")&&!shipOut[floor][x][y+1].equals("ship")) {
							return null;
						}
						if(!shipOut[floor][x-1][y].equals("ship")&&!shipOut[floor][x][y-1].equals("ship")) {
							return null;
						}
						if(!shipOut[floor][x][y+1].equals("ship")&&!shipOut[floor][x][y-1].equals("ship")) {
							return null;
						}
					}
				}
			}
		}
		
		for(int floor=0; floor<f;floor++) {
			for(int x=0; x<shipOut[0].length;x++) {
				for(int y=0; y<shipOut[0][0].length; y++) {
					if(shipOut[floor][x][y].equals(""+(roomMin-1))){
						return shipOut;
					}
				}
			}
		}
		return null;
	}
	
	public static String[][][] move(int x, int y, int f, String[][][] in,int nx, int ny, int nf){
		String[][][] out=in;
		if(nf==1) {
			out[f+nf][(x)*2+1][(y)*2+1]="down";
			out[f][(x)*2+1][(y)*2+1]="up";
		}else if(nf==-1) {
			out[f+nf][(x)*2+1][(y)*2+1]="up";
			out[f][(x)*2+1][(y)*2+1]="down";
		}else {
			out[f+nf][(x+nx)*2+1][(y+ny)*2+1]="d";
			out[f][(x)*2+nx+1][(y)*2+ny+1]="floor";
		}
		return out;
	}
	
	public static int[] checkRoom(String[][][] in){
		int[] check=new int[in.length*in[0].length*in[0][0].length];
		check[0]=-1;
		int i=0;
		for(int floor=0; floor<in.length;floor++) {
			for(int x=3; x<in[0].length-1;x+=2) {
				for(int y=3; y<in[0][0].length-1; y+=2) {
					if(in[floor][x][y].equals("floor") && in[floor][x+2][y].equals("floor")&&in[floor][x+2][y+2].equals("floor")&&in[floor][x][y+2].equals("floor")){
						check[i]=y;
						i++;
						check[i]=x;
						i++;
						check[i]=floor;
						i++;
					}
				}
			}
		}
		int[] out=new int[i+1];
		for(int i2=0; i2<i; i2++) {
			out[i2]=check[i2];
		}
		if(check[0]==-1) {
			out[0]=-1;
		}
		return out;
	}
	
	public static String[][][] makeRoom(String[][][] in,int x,int y,int f, int r){
		String[][][] out=in;
		out[f][x][y]=""+r;
		out[f][x][y+1]=""+r;
		out[f][x][y+2]=""+r;
		out[f][x+1][y]=""+r;
		out[f][x+2][y]=""+r;
		out[f][x+1][y+1]=""+r;
		out[f][x+1][y+2]=""+r;
		out[f][x+2][y+1]=""+r;	
		out[f][x+2][y+2]=""+r;	
		int i=0;
		if(out[f][x][y-1]=="floor" ||out[f][x][y-1]=="door") {
			i++;
		}
		if(out[f][x+2][y-1]=="floor" ||out[f][x+2][y-1]=="door") {
			i++;
		}
		if(i>0) {
			if(!out[f][x+1][y-2].contentEquals("ship") && !out[f][x+1][y-2].contentEquals("n") && !out[f][x+1][y-2].contentEquals("floor") && !out[f][x+1][y-2].contentEquals("door")) {
				out[f][x+1][y-1]="door";
				out[f][x][y-1]="ship";
				out[f][x+2][y-1]="ship";
			}else {
				if(out[f][x][y-1]=="floor") {
					out[f][x][y-1]="door";
				}
				if(out[f][x+2][y-1]=="floor") {
					out[f][x+2][y-1]="door";
				}			
			}
		}
		if(out[f][x+1][y-2]=="floor" && i==2){
			out[f][x+1][y-2]="ship";
		}
		
		i=0;
		if(out[f][x+3][y]=="floor" ||out[f][x+3][y]=="door") {
			i++;
		}
		if(out[f][x+3][y+2]=="floor" ||out[f][x+3][y+2]=="door") {
			i++;
		}
		if(i>0) {
			if(out[f][x+4][y+1]!="ship" && out[f][x+4][y+1]!="n" && out[f][x+4][y+1]!="floor" && out[f][x+4][y+1]!="door") {
				out[f][x+3][y+1]="door";
				out[f][x+3][y]="ship";
				out[f][x+3][y+2]="ship";
			}else {
				if(out[f][x+3][y]=="floor") {
					out[f][x+3][y]="door";
				}
				if(out[f][x+3][y+2]=="floor") {
					out[f][x+3][y+2]="door";
				}			
			}
		}
		if(out[f][x+4][y+1]=="floor" && i==2){
			out[f][x+4][y+1]="ship";
		}
		
		i=0;
		if(out[f][x+2][y+3]=="floor" ||out[f][x+2][y+3]=="door") {
			i++;
		}
		if(out[f][x][y+3]=="floor" ||out[f][x][y+3]=="door") {
			i++;
		}
		if(i>0) {
			if(out[f][x+1][y+4]!="ship" && out[f][x+1][y+4]!="n" && out[f][x+1][y+4]!="floor" && out[f][x+1][y+4]!="door") {
				out[f][x+1][y+3]="door";
				out[f][x+2][y+3]="ship";
				out[f][x][y+3]="ship";
			}else {
				if(out[f][x+2][y+3]=="floor") {
					out[f][x+2][y+3]="door";
				}
				if(out[f][x][y+3]=="floor") {
					out[f][x][y+3]="door";
				}			
			}
		}
		if(out[f][x+1][y+4]=="floor" && i==2){
			out[f][x+1][y+4]="ship";
		}
		
		i=0;
		if(out[f][x-1][y+2]=="floor" ||out[f][x-1][y+2]=="door") {
			i++;
		}
		if(out[f][x-1][y]=="floor" ||out[f][x-1][y]=="door") {
			i++;
		}
		if(i>0) {
			if(out[f][x-2][y+1]!="ship" && out[f][x-2][y+1]!="n" && out[f][x-2][y+1]!="floor" && out[f][x-2][y+1]!="door") {
				out[f][x-1][y+1]="door";
				out[f][x-1][y+2]="ship";
				out[f][x-1][y]="ship";
			}else {
				if(out[f][x-1][y+2]=="floor") {
					out[f][x-1][y+2]="door";
				}
				if(out[f][x-1][y]=="floor") {
					out[f][x-1][y]="door";
				}			
			}
		}
		if(out[f][x-2][y+1]=="floor" && i==2){
			out[f][x-2][y+1]="ship";
		}
		return out;
	}
}
