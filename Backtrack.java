class Backtrack
{
    public static void backtrack(String str, String permut, int index)
    {
        if(str.length()==0){
            System.out.println(permut);
            return;
        }
        for(int i=0;i<str.length();i++){
            char Currchar=str.charAt(i);
            String Newchar=str.substring(0, i)+str.substring(i+1);
            backtrack(Newchar, permut+Currchar, index+1);

        }
    } 
    public static void main(String[]args)
    {
        String str = "ABC";
        backtrack(str, "", 0);

    }
}