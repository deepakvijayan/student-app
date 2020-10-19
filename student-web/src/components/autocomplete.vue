
<template>
  <div class="autocomplete">
    <input
      type="text"
      :placeholder="placeHolderText"
      v-model="keywordSearch"
      class="form-textinput"
      :class="{'loading-circle': keywordSearch.length > 2, 'hide-loading-circle': resultItems.length > 0 || (resultItems.length == 0 && !autoCompleteProgress)}"
      @keyup="!autoCompleteProgress ? onKeyUp(keywordSearch) : ''" />
    <ul class="autocomplete-results" v-if="resultItems.length > 0">
      <li
        class="autocomplete-result"
        v-for="(item, i) in resultItems"
        :key="i"
        @click="keywordSearch = ''; 
        onSelected(item[itemId], item[itemText]);">
        {{ item[itemText] }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "autocomplete",
  props: {
    placeHolderText: String,
    onKeyUp: Function,
    onSelected: Function,
    resultItems: Array,
    autoCompleteProgress: Boolean,
    itemText: String,
    itemId: String,
  },
  data() {
    return {
      keywordSearch: "",
    };
  },
};
</script>

<style scoped>
/*********** AUTO COMPLETE ************/
.loading-circle {
  background-color: #ffffff;
  background-image: url("../assets/loading.gif");
  background-size: 16px 16px;
  background-position: right center;
  background-repeat: no-repeat;
}

.hide-loading-circle {
  background: none;
}

.form-textinput {
  padding: 8px 10px;
  border-radius: 5px;
  border: solid 1px #ccc;
  width: 800px;
  font-size: large;
}

.autocomplete {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.autocomplete-results {
 
  margin: 0;
  border: 1px solid #eeeeee;
  height: 150px;
  overflow: auto;
  background-color: #fdf8f3;
  border-radius: 5px;
  font-size: medium;
  padding: 8px 10px;
  border-radius: 5px;
  border: solid 1px #ccc;
  width: 800px;
  font-size: large;
}

.autocomplete-result {
  list-style: none;
  text-align: left;
  padding: 4px 10px;
  cursor: pointer;
}

.autocomplete-result:hover {
  background-color: #4aae9b;
  color: white;
}
/*********** END AUTO COMPLETE ************/
</style>