<template>
  <div id="app">
    <p>Student Directory</p>
    <autocomplete 
		:place-holder-text="placeHolderInputText"
		:result-items="autoCompleteResult"
		:on-key-up="onKeyUpAutoCompleteEvent"
		:on-selected="onSelectedAutoCompleteEvent"
		:auto-complete-progress="autoCompleteProgress"
		:item-text="autoCompleteText"
		:item-id="autoCompleteFieldId">
	</autocomplete>
  <p v-if="selectedStudentName !==''" :class="result-display">Selected student name is : {{selectedStudentName}}</p>
  </div>
</template>

<script>

import autocomplete from './components/autocomplete'
import axios from 'axios'

export default {
  name: "App",
  components: {
    autocomplete
  },
  data () {
    return {
      placeHolderInputText: 'Enter student name. E.g. Matt or Deepak',
      autoCompleteResult: [],
      autoCompleteProgress: false,
      autoCompleteText: "name",
      autoCompleteFieldId: "alpha3Code",
      selectedStudentName: ''
    }
  },
  methods: {
		onSelectedAutoCompleteEvent(id, text){
			this.autoCompleteProgress = false;
			this.autoCompleteResult = [];
      this.selectedStudentName=text;
		},
		onKeyUpAutoCompleteEvent(keywordEntered){
			//reset
			this.autoCompleteResult = [];
			this.autoCompleteProgress = false;
			if(keywordEntered.length >= 2){
				this.autoCompleteProgress = true;
        axios.get("http://localhost:8080/students/" + keywordEntered)
				.then(response => {
					var newData = [];
					response.data.forEach(function(item){
							newData.push(item);
					});
					this.autoCompleteResult = newData;
					this.autoCompleteProgress = false;
				})
				.catch(e => {
          console.log(e);
					this.autoCompleteProgress = false;
					this.autoCompleteResult = [];
				})
			}else{
				this.autoCompleteProgress = false;
				this.autoCompleteResult = [];
			}
		}
	}
};
</script>

<style>

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  justify-content: center;
  padding-top: 100px;
  height: 100%;
  font-size: xx-large;
}

.result-display {
 font-size: medium;
}


</style>
