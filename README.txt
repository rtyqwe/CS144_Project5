Project by:
Robert Nguyen 803879361
Ryan Chan 503943165


Q3:
First I used @media markers in the CSS file to check if the width of the browser is 320px.
Then, I set the style of the <body> tag to be "overflow-x: hidden;". This style crops the page to its width and hides the excess content (thus disabling scrolling).

Q4:
The width of the textbox component can fit the screen width because once the @media is triggered for a mobile browser (with max-width = 320px), all the textbox and blocks containing are resized to relative widths <= 100% of the body. Therefore, no component in the page including the textbox would go over the screen horizontally. 


Resources:
http://lampload.com/Remove-Horizontal-Scrolling-in-CSS.html