#!c:\perl\bin\perl.exe
print "content_type:text/html \n\n";
print "<HTML><BODY>";
$buffer=$ENV{"QUERY_STRING"};
#open(OUTF,">>data.txt");
#print OUTF "$buffer";
#put "saving done";
#close (OUTF);
print "$buffer";

print "</body></html>";
