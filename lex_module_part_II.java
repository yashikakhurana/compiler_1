
void   Open_File();
void   Demage_Lexeme();
int    Search(char[256],int);
void   analyze();
void   Skip_Comment();
void   Read_String();
void   Is_Keyword_Or_Not();
void   Is_Identifier_Or_Not();
void   Is_Operator_Or_Not();
void   Read_Number();
void   Is_Special_Or_Not();
void   Is_Comparison_Or_Not();
void   Add_To_Lexical (char[256],int,char[256]);
void   Print_ST();
void   Print_TOKEN();
void   Token_Attribute();


/***********************************************************
Function to print the TOKENS.TXT .
***********************************************************/

void Print_TOKEN()
{
  int flag=0;

  fp=fopen("source.txt","r");

    if(fp==NULL)
    {
       printf("!!!Can't open input file - source.txt!!!");
       getch();
       exit(0);
    }

  else

    {
	if ((token=fopen("TOKENS.TXT","w"))==NULL)
	  printf("The file ST.TXT cat not open. 
");

      else

      {
	ch=fgetc(fp);

	while (!(feof(fp)))
	{

	  if (ch==' ' && !flag)
	  {
	    do
	      ch=fgetc(fp);
	    while (ch==' ');

	    fseek(fp,-2,1);
	    ch=fgetc(fp);
	    flag=1;
	  }

	  if (ch!='
' && ch!='	')
	    System.out.println(token,"%c",ch);

	  if (ch=='
')
	  {
	    System.out.println(token,"
");
	    Token_Attribute();
	    i++;
	    flag=0;
	  }

	  ch=fgetc(fp);
	}
      }
    }
    fclose(fp);
    fclose(token);
}
			       
/**********start of lex module part-II***********/			       

/***********************************************************
Function to put the token and atrribute in TOKENS.TXT .
************************************************************/

void Token_Attribute()
{
  lex x=head;
  int j;

  while (x!=NULL)
  {
    if (x->line[0]==i)
    {
      System.out.println(token,"token : %-4s	",x->type);

      if ((strcmp(x->type,"num")==0)         ||
	 (strcmp(x->type,"keyword")==0)      ||
	 (strcmp(x->type,"identifier")==0))

      {
	  System.out.println(token,"attribute : line#=%-4d 
",i);
      }

      else

      {
	  System.out.println(token,"attribute : %-4s 
",x->data);
      }

    }
    x=x->next;
  }
  System.out.println(token,"
");
}

/**********************************************************
Function to create lexical analysis.
***********************************************************/

void analyze()
{

  ch=fgetc(fp);                      //Read character.

  while(!feof(fp))                   //While the file is not end.
  {

      if(ch=='
')                   //Compute # of lines in source.txt 
.
	  {
	    line++;
	    ch=fgetc(fp);
	  }

      if(isspace(ch) && ch=='
' )
      {
	  line++;
	  ch=fgetc(fp);
      }
      if(isspace(ch) && ch!='
' )          //The character is space.
	  ch=fgetc(fp);


      if(ch=='/' || ch=='"')    //Function for skipping comments in the
file
	  Skip_Comment();	//and '"' with display statements.


      if(isalpha(ch))              //The character is leter.
	{
	    Read_String();
	    Is_Keyword_Or_Not();
	    Is_Operator_Or_Not();
	    Is_Identifier_Or_Not();
	}


      if(isdigit(ch))             //The character is digit.
	 Read_Number();


      if (ch==';')                //The character is semicolon.
	Add_To_Lexical(";",line,"semicolon");


      if (ch==':')                //The character is colon.
	Add_To_Lexical(":",line,"colon");


      if (ch==',')                //The character is comma.
	Add_To_Lexical(",",line,"comma");


      if (ch=='(')                //The character is parenthesis.
	Add_To_Lexical("(",line,"parenthesis");


      if (ch==')')                //The character is parenthesis.
	Add_To_Lexical(")",line,"parenthesis");

				 //The character is comparison_operator
      if (ch=='<' || ch=='=' || ch=='>')
	Is_Comparison_Or_Not();


      Is_Special_Or_Not();       //After failed scaning in before cases
				 //check the character is special or not.
      Demage_Lexeme();

      if(isspace(ch) && ch=='
' )
      {
	  line++;
	  ch=fgetc(fp);
      }
      else
      ch=fgetc(fp);
  }
}






	
