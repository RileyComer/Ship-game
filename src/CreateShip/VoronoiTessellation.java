package CreateShip;


public class VoronoiTessellation {
	
	public static boolean[][] shipGen(int w, int h, int d, int min, int max,boolean[][] add){
		ShipRandom rand= new ShipRandom(null);
		String[][] vT= new String[w][h];
		boolean[][] out=new boolean[vT.length][vT[0].length];
		boolean shipGood=false;
		while(!shipGood) {
			for(int y=0; y<vT.length;y++) {
				for(int x=0; x<vT[y].length;x++) {
					vT[x][y]="";
				}
			}
			for(int i=0; i<d; i++) {
				int x, y;
				x=(int)(rand.nextDouble()*w);
				y=(int)(rand.nextDouble()*h);
				while(vT[x][y].equals(null)) {
					x=(int)(rand.nextDouble()*w+1);
					y=(int)(rand.nextDouble()*h+1);
				}
				vT[x][y]=i+"-0";
			}
			shipGood=true;
			boolean done=false;
			for(int layer=0;!done;layer++) {
				done=true;
				for(int x=0; x<w;x++) {
					for(int y=0; y<h;y++) {
						int nx=x-1, ny=y-1;
						if((!(vT[x][y].equals(""))&&vT[x][y].substring((vT[x][y].indexOf("-")+1)).equals(""+layer))) {	
							for(int i=0; i<4;i++) {
								if(i==0) {
									if(y==vT[x].length-1) {
										ny=0;
									}else {
										ny=y+1;
									}
									nx=x;
								}else if(i==1) {
									if(x==vT.length-1) {
										nx=0;
									}else {
										nx=x+1;
									}
									ny=y;
								}else if(i==2) {
									if(x==0) {
										nx=vT.length-1;
									}else {
										nx=x-1;
									}
									ny=y;
								}else if(i==3) {
									if(y==0) {
										ny=vT[x].length-1;
									}else {
										nx=y-1;
									}
									nx=x;
								}
								if((vT[nx][ny].equals(""))) {
									vT[nx][ny]=(vT[x][y].substring(0,(vT[x][y].indexOf("-")))+"-"+(layer+1));
									done=false;
								}
							}
							vT[x][y]=(vT[x][y].substring(0,(vT[x][y].indexOf("-")))+"-!");
						}
					}
				}
			}
			for(int x=(w/2); x<w;x++) {
					for(int y=0; y<h;y++) {
						vT[x][y]=vT[((w-1)-x)][y];
					}
			}
			String ship=vT[w/2][h/2];
			vT[(w/2)][(h/2)-1]=ship;
			vT[(w/2)-1][(h/2)-1]=ship;
			int i=0;
			for(int x=0; x<w;x++) {
				for(int y=0; y<h;y++) {
					if(!vT[x][y].equals(ship)) {
						out[x][y]=false;
					}else {
						out[x][y]=true;
						i++;
					}
				}
			}
			if(rand.nextDouble()*2>1){
				boolean[][] b=out;
				out=new boolean[b.length][b[0].length];
				for(int x=0; x<w;x++) {
					for(int y=0; y<h;y++) {
						if(b[x][y]) {
							out[x][out[0].length-1-y]=true;
						}
					}
				}
			}
			
			if(add!=null) {
				for(int y=0; y<add[0].length;y++) {
					for(int x=0; x<add.length;x++) {
						if(add[x][y]) {
							if(!out[x][y]) {
								out[x][y]=true;
								i++;
							}
						}
					}
				}
				int x=w/2;
				for(int y=0; y<out[0].length;y++) {
					if(out[x][y]&&y>0) {
						out[x][y-1]=out[x][y];
						out[x-1][y-1]=out[x][y];
					}
				}
			}
			if((i<min)||(i>max)) {
				shipGood=false;
			}else {
				for(int y=0; y<out[0].length;y++) {
					if(out[0][y]) {
						shipGood=false;
					}
					
					if(out[out.length-1][y]) {
						shipGood=false;
					}
				}
				
				for(int x=0; x<out.length;x++) {
					if(out[x][0]) {
						shipGood=false;
					}
					if(out[x][out[0].length-1]) {
						shipGood=false;
					}
				}
			}
		}
		return out;
	}
}
