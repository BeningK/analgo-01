#include <iostream>
using namespace std;

long long int Rek(int y){
    if (y==1){
        return 2;
    }
    else{
        return 2* Rek(y-1);
    }
       
}
int main(int argc, char** argv) {
	int y =6;
	cout<<"Hasil Rekrusif dari 2^"<<y<<" = ";
	
	cout <<Rek(y) <<endl<<endl;
	

} 
