package com.homeworkgoto.cardsgame.service;

import com.homeworkgoto.cardsgame.data.domain.DeckCard;
import com.homeworkgoto.cardsgame.data.repository.DeckCardRepository;
import com.homeworkgoto.cardsgame.mapper.CardMapper;
import com.homeworkgoto.cardsgame.model.CardDescriptor;
import com.homeworkgoto.cardsgame.model.Rank;
import com.homeworkgoto.cardsgame.model.Suit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeckCardServiceImpl implements DeckCardService {

    private final DeckFactoryImpl deckFactory;
    private final DeckCardRepository deckCardRepository;
    private final CardMapper cardMapper;

    public void addDeck(Integer gameId) {
        List<DeckCard> cards = deckFactory.createDeck().stream().map(cardDto -> {
            DeckCard deckCard = cardMapper.toCard(cardDto);
            deckCard.setGameId(gameId);
            return deckCard;
        }).collect(Collectors.toList());

        deckCardRepository.saveAll(cards);
    }

    public void shuffleCards(Integer gameId) {

        List<DeckCard> list = deckCardRepository.findByGameIdAndDealtToPlayerIsNull(gameId);
        Iterator<DeckCard> it = list.iterator();
        int cardIndex = 1;
        while (it.hasNext()) {
            int rand = (int)(Math.random() * ((list.size() - 1)));
            DeckCard c = list.get(rand);
            c.setCardPosition(cardIndex++);
            deckCardRepository.save(c);
            it.next();
        }
    }

    public void dealCards(Integer gameId, Integer playerId) {
        List<DeckCard> list = getUndealtCard(gameId);
        DeckCard deckCard = list.get(list.size()-1);
        deckCard.setDealtToPlayer(playerId);
        deckCardRepository.save(deckCard);
    }

    public Map<Suit, Integer> getUndealtCardsCound(Integer gameId) {
        List<DeckCard> list = deckCardRepository.findByGameIdAndDealtToPlayerIsNull(gameId);
        Map<Suit, Integer> countSuit = new HashMap<>();
        for(DeckCard deckCard: list) {
            int count = countSuit.getOrDefault(Suit.valueOf(deckCard.getSuit()), 0);
            countSuit.put(Suit.valueOf(deckCard.getSuit()), count + 1);
        }
        return countSuit;
    }

    public List<CardDescriptor> showUndealtCard(Integer gameId) {
        return getUndealtCard(gameId).stream().map(deckCard -> {
                return CardDescriptor.builder()
                        .rank(Rank.valueOf(deckCard.getRank()))
                        .suit(Suit.valueOf(deckCard.getSuit()))
                        .build();
        }).collect(Collectors.toList());
    }


    private List<DeckCard> getUndealtCard(int gameId) {
        List<DeckCard> list = deckCardRepository.findByGameIdAndDealtToPlayerIsNull(gameId);
        list.sort(Comparator.comparing(DeckCard::getCardPosition));
        return list;
    }

}
