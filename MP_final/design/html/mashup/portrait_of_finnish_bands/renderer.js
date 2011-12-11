var directive =
{
	'li': {
    'part <- finnish_bands.parts': {
     	'a.caption': 'part.caption',
    	 'ul.google': { 
			       'google <- part.google_search.parts': {
			       'div.google_caption':'google.caption',
			       'a.google_body': 'google.body',
			       'a.google_body@href': 'google.url'},
         			},
         'ul.flickr': {
  				    'flickr <- part.flickr.parts': {
					'img.imgURL@src':'flickr.imgURL',
					'img.imgURL@alt': 'flickr.imgURL',
					'a.url@href':'flickr.url'}
         			},
      'div.parts': function(ctxt){
        return ctxt.part.item.parts ? rfn(ctxt.part.item):'';
      }
    }
  }
};