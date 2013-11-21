function SuggestionProvider(suggestions){
    this.suggestions = suggestions;
}

SuggestionProvider.prototype.setSuggestions(suggestions) = function(suggestions) {
    this.suggestions = suggestions;
}

SuggestionProvider.prototype.requestSuggestions = function(oAutoSuggestControl, bTypeAhead) {
    var aSuggestions = [];
    var sTextboxValue = oAutoSuggestControl.textbox.value;

    if (sTextboxValue.length > 0){

        for (var i=0; i < this.suggestions.length; i++) { 
            if (this.suggestions[i].indexOf(sTextboxValue) == 0) {
                aSuggestions.push(this.suggestions[i]);
            } 
        }
    }

    oAutoSuggestControl.autosuggest(aSuggestions, bTypeAhead);
};
