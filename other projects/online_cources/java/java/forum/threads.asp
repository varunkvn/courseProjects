<script language="JScript" Runat="server">
/* threads.asp
 * Copyright (c) 2000 by David M. Read
 */

// Create the thread object that contains all the top-level posts
var threads = new MakeThreadObject();

function AddPost (post_id, thread_id, parent_id, subject, author, postdate)
{
   if (parent_id == 0) // it's a new thread
      AddChildMessage (threads, post_id, thread_id, parent_id, subject, author, postdate);
   else
   {
      var parent = FindParentThread (threads, parent_id);
      if (parent != null)
         AddChildMessage (parent, post_id, thread_id, parent_id, subject, author, postdate);
      else // fall-back code in case we can't find the parent--it's not right, but it's better than nothing
         AddChildMessage (threads, post_id, thread_id, parent_id, subject, author, postdate);
   }
}

function AddChildMessage (threadObject, post_id, thread_id,  parent_id, subject, author, postdate)
{
   threadObject.child_count++;
   threadObject.children[threadObject.child_count] = new MakeThreadObject (thread_id, post_id, parent_id, subject, author, postdate);
}

function FindParentThread (threadObject, parent_id)
{
   // Loop through all children of this object
   for (var i = 0; i <= threadObject.child_count; i++)
   {
      // Did we find the node we're looking for?
      if (threadObject.children[i].post_id == parent_id)
      {
         // Yes?  Increment the child counter
         threadObject.children[i].num_descendants++;
         // return the object so we can use it.
         return threadObject.children[i];
      }
      else // No?  Go search the child for our node
      {
         var temp = FindParentThread (threadObject.children[i], parent_id);
         if (temp != null)  // Did we succeed?
         {
            // Yes?  Increment the child counter
            threadObject.children[i].num_descendants++;
            // return the object
            return temp;
         }
      }
   }
   // Oops, no luck finding the desired node.
   return null;
}

function MakeThreadObject(thread_id, post_id, parent_id, subject, author, postdate)
{
   this.thread_id = thread_id
   this.parent_id = parent_id;
   this.post_id = post_id;
   this.subject = new String (subject);
   this.author = new String (author);
   this.postdate = new String (postdate);
   this.children = new Array();
   this.child_count = -1;
   this.num_descendants = 0;
}

function ShowPost (post, level)
{
   writeln ('<table width="100%" cellspacing=0 cellpadding=0><tr valign="top"><td>');
         
   // This indents the post
   write ('<table cellpadding=1 cellspacing=0 border=0><tr valign="top"><td>');
   write ('<img src="../images/pixel.gif" height=1 width=' + (1+25*level) + '>');
      
   // Show the expand/collapse/no-children graphics
   if (post.num_descendants > 0)
   {
      if (post.expanded == 0)
         write ('<img src="../images/expand.gif" height=11 width=11 border=0>');
      else
         write ('<img src="../images/collapse.gif" height=11 width=11 border=0>');
   }
   else
      write ('<img src="../images/nochildren.gif" height=11 width=11 border=0>');
   //write ('</td><td valign="top">');
      
   // Create the anchor which links to the post
   write ('<a href="forum.asp?smode=2&post_id=' + post.post_id + '&thread_id=' + post.thread_id + '" class=n3>');
   write ('<font face="Arial,helvetica,verdana,geneva">');
   write (post.author);
   write (' - ' + post.postdate);
   write (' - ' + post.subject);
   write (' - (' + post.num_descendants + ')</font></a></td></tr></table>');

   writeln ('</td></tr></table>');
}

function ShowPage ()
{
   ShowHeader ();
         
   writeln ('<center>');
   ShowThreads (threads, 0);
   writeln ('</center>');

   ShowFooter ();
}

function ShowThreads (threadObject, level)
{
   for (var i = 0; i <= threadObject.child_count; i++)
   {
      var post = threadObject.children[i];
      ShowPost (post, level);
      ShowThreads (post, level + 1);
   }
}

function ShowMessageThreads ()
{
      writeln ('<center><table width="98%" border=0 cellspacing=0>');
      ShowThreads (threads, 0);
      writeln ('</table></center>');
}

</script>