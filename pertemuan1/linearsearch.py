import time
start = time.clock()

def search(arr, n, x): 
  
    for i in range (0, n): 
        if (arr[i] == x): 
            return i; 
    return -1; 
  
arr = [ 2, 3, 4, 10, 40 ]; 
x = 10; 
n = len(arr); 
result = search(arr, n, x) 
if(result == -1): 
    print("Element tidak ditemukan dalam array") 
else: 
    print("Element ditemukan pada array di index ke", result); 

end = time.clock()
print("--- Runtime: %.2gs ---" % (end-start))